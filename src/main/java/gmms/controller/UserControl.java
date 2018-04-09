package gmms.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.Role;
import gmms.domain.db.SysPlaza;
import gmms.domain.db.Users;
import gmms.domain.param.UserParam;
import gmms.service.BaseInformationService;
import gmms.service.MsgService;
import gmms.service.UsersService;
import gmms.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by yangjb on 2017/7/16.
 * helloWorld
 */

@RequestMapping("/user")
@Controller
public class UserControl extends BaseControl {
    @Autowired
    private UsersService usersService;
    @Autowired
    private BaseInformationService baseInformationService;
    @Autowired
    private MsgService msgService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long id, Model model) {
        if (id != null) {
            Users user = usersService.getById(id);
            model.addAttribute("user", user);
        }

        model.addAttribute("roleList", usersService.listAllRole());
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        return "cardmaintenanceadmin/users/user-add";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Users users, long roleId) {
        long userId = 0;
        if (users.getId() != null) {
            userId = users.getId();
            Users oldUser = usersService.getById(users.getId());
            oldUser.setUserNo(users.getUserNo());
            oldUser.setUserName(users.getUserName());
            oldUser.setTelphone(users.getTelphone());
            //oldUser.setEmail(users.getEmail());

            oldUser.setRemark(users.getRemark());
            oldUser.setPassword(users.getPassword());
            oldUser.setGmtModify(new Date());
            if (!oldUser.isSuperAdmin()) {
                Role role = usersService.getByRoleId(roleId);
                oldUser.setRoles(Lists.newArrayList(role));
                oldUser.setSysPlaza(users.getSysPlaza());
            }
            /*SysPlaza sysPlaza=baseInformationService.findSysPlaza(plazaNo);
            if(null!=sysPlaza){
                oldUser.setSysPlaza(Lists.newArrayList(sysPlaza));
            }*/
            usersService.saveOrUpdate(oldUser);
        } else {
            Role role = usersService.getByRoleId(roleId);
            users.setRoles(Lists.newArrayList(role));
            if(users.isSuperAdmin()){
                SysPlaza sysPlaza = new SysPlaza();
                sysPlaza.setPlaNo("0000");
                users.setSysPlaza(sysPlaza);
            }
            /*SysPlaza sysPlaza=baseInformationService.findSysPlaza(plazaNo);
            if(null!=sysPlaza){
                users.setSysPlaza(Lists.newArrayList(sysPlaza));
            }*/
            users.setGmtCreate(new Date());
            users.setLastLoginTime(DateUtil.parseDate("2000-01-01", "yyyy-MM-dd"));
            Users save = usersService.saveOrUpdate(users);
            userId = save.getId();
        }
        return AjaxResponseBodyFactory.createSuccessBody(true, userId);
    }

    @RequestMapping(value = "/list")
    public String list(UserParam userParam, Model model) {
        List<Users> users = usersService.listAllUser(userParam);
        model.addAttribute("userList", users);
        model.addAttribute("roleList", usersService.listAllRole());
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", userParam);
        return "cardmaintenanceadmin/users/user-list";
    }


    @ResponseBody
    @RequestMapping(value = "usernameNotExist")
    public String usernameNotExist(String userNo) {
        Preconditions.checkNotNull(userNo, "username 不能为空");
        Users users = usersService.findByName(userNo);
        return users == null ? "true" : "false";
    }

/*    @ResponseBody
    @RequestMapping(value = "telPhoneNotExist")
    public String telPhoneNotExist(String telphone) {
        Preconditions.checkNotNull(telphone, "telphone 不能为空");
        List<Users> usersList = usersService.findByTelphone(telphone);
        return CollectionUtils.isEmpty(usersList) ? "true" : "false";
    }*/


    @ResponseBody
    @RequestMapping(value = "checkPassword")
    public String checkPassword(String oldPassword) {
        Preconditions.checkNotNull(oldPassword, "oldPassword 不能为空");
        Users currentUser = getCurrentUser();
        return String.valueOf(StringUtils.endsWithIgnoreCase(currentUser.getPassword(), oldPassword));
    }

    @ResponseBody
    @RequestMapping(value = "changePassword")
    public String changePassword(String oldPassword, String password) {
        Preconditions.checkNotNull(password, "password 不能为空");
        Users currentUser = getCurrentUser();
        if (StringUtils.equalsIgnoreCase(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(password);
            currentUser.setGmtModify(new Date());
            usersService.saveOrUpdate(currentUser);
            return "ok";
        }

        return "fail";
    }



    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(long userId) {
        Users users = usersService.getById(userId);
        if (users.isSuperAdmin()) {
            return "该用户是超级管理员,不可删除!";
        }
        //如果删除的是美工,需要转移设计图到admin下面。免得设计图跟着一起被删了
        //deleteUiAsin(users);
        usersService.delete(getCurrentUser(), users);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public String deleteAll(String userIds) {
        String[] split = userIds.split(",");
        List<Users> usersList = Lists.newArrayList();
        for (String idStr : split) {
            long id = Long.parseLong(idStr);
            Users users = usersService.getById(id);
            if (!users.isAdmin()) {
                usersList.add(users);
            }

           // deleteUiAsin(users);
        }

        usersService.delete(getCurrentUser(), usersList);
        return "ok";
    }


    @ResponseBody
    @RequestMapping(value = "/addplaza", method = RequestMethod.POST)
    public String addplaza(SysPlaza sysPlaza,Long userId) {
        Users currentUser = getCurrentUser();
        if(null!=currentUser){
            sysPlaza.setPlaUserId(String.valueOf(currentUser.getId()));
            sysPlaza.setPlaUserName(currentUser.getUserName());
            sysPlaza.setPlaUserNo(currentUser.getUserNo());
        }
        sysPlaza.setPlaModifyTime(new Date());
        sysPlaza.setPlaInUse("1");
        SysPlaza save = baseInformationService.saveOrUpdate(sysPlaza);
        return AjaxResponseBodyFactory.createSuccessBody(true, save.getPlaNo());
    }

    @ResponseBody
    @RequestMapping(value = "sysPlazaNotExist")
    public String sysPlazaNotExist(String plaNo) {
        Preconditions.checkNotNull(plaNo, "plazaNo 不能为空");
        SysPlaza sysPlaza = baseInformationService.findSysPlaza(plaNo);
        return sysPlaza == null ? "true" : "false";
    }
    @RequestMapping(value = "/plazalist")
    public String plazalist(UserParam userParam, Model model) {
        List<SysPlaza> sysPlazas = baseInformationService.sysPlazaListAll();
        model.addAttribute("sysPlazaList", sysPlazas);
        //model.addAttribute("roleList", usersService.listAllRole());
        //model.addAttribute("param", userParam);
        return "cardmaintenanceadmin/users/plaza-list";
    }


    @ResponseBody
    @RequestMapping(value = "/deleteplaza", method = RequestMethod.GET)
    public String deleteplaza(String plazaNo) {
        SysPlaza sysPlaza = baseInformationService.findSysPlaza(plazaNo);
        List<Users>  usersList= usersService.findBySysPlaza(sysPlaza);
        if(null!=usersList&&usersList.size()>0){
            return "userExist";
        }else {
            baseInformationService.delete(getCurrentUser(), sysPlaza);
            return "ok";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAllplaza", method = RequestMethod.GET)
    public String deleteAllplaza(String plazaNos) {
        String[] split = plazaNos.split(",");
        List<SysPlaza> sysPlazaList = Lists.newArrayList();
        for (String idStr : split) {
            /*long id = Long.parseLong(idStr);*/
            SysPlaza sysPlaza = baseInformationService.findSysPlaza(idStr);
            sysPlazaList.add(sysPlaza);
        }
        baseInformationService.delete(getCurrentUser(), sysPlazaList);
        return "ok";
    }

}
