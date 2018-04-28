package gmms.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import gmms.domain.AjaxResponseBodyFactory;
import gmms.domain.db.*;
import gmms.domain.param.UserParam;
import gmms.service.BaseInformationService;
import gmms.service.MsgService;
import gmms.service.UsersService;
import gmms.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
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
    public String add(Users users, long roleId) throws UnsupportedEncodingException {
        long userId = 0;
        if (users.getId() != null) {
            userId = users.getId();
            Users oldUser = usersService.getById(users.getId());
            oldUser.setUserNo(users.getUserNo());
            oldUser.setUserName(users.getUserName());
            oldUser.setTelphone(users.getTelphone());
            oldUser.setFax(users.getFax());
            oldUser.setEmail(users.getEmail());
            oldUser.setiDCard(users.getiDCard());
            oldUser.setAddress(users.getAddress());
            oldUser.setZip(users.getZip());
            //oldUser.setEmail(users.getEmail());

            oldUser.setRemark(users.getRemark());
            oldUser.setPassword(MD5.GetMD5Code(users.getPassword()));
            oldUser.setGmtModify(new Date());
            if (!oldUser.isSuperAdmin()||!oldUser.isTagAdmin()) {
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
            if(users.isSuperAdmin()||users.isTagAdmin()){
                SysPlaza sysPlaza = new SysPlaza();
                sysPlaza.setPlaNo(0l);
                users.setSysPlaza(sysPlaza);
            }
            /*SysPlaza sysPlaza=baseInformationService.findSysPlaza(plazaNo);
            if(null!=sysPlaza){
                users.setSysPlaza(Lists.newArrayList(sysPlaza));
            }*/
            if(!StringUtil.isEmpty(users.getPassword())){
                users.setPassword(MD5.GetMD5Code(users.getPassword()));
            }
            users.setGmtCreate(new Date());
            users.setLastLoginTime(DateUtil.parseDate("2000-01-01", "yyyy-MM-dd"));
            Users save = usersService.saveOrUpdate(users);
            userId = save.getId();
        }
        return AjaxResponseBodyFactory.createSuccessBody(true, userId);
    }


    @RequestMapping(value = "/list")
    public String list(UserParam userParam, Model model) {
       /* List<Users> users = usersService.listAllUser(userParam);
        model.addAttribute("userList", users);*/
        model.addAttribute("roleList", usersService.listAllRole());
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", userParam);
        return "cardmaintenanceadmin/users/user-list";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public String userpage(UserParam userParam) {
        int pageNo = userParam.getStart() / userParam.getLength() + 1;
        Pagination<Users> pagination = new Pagination<Users>();
        Page<Users> users = usersService.listAllUserPage(userParam, pageNo, userParam.getLength());
        pagination.setData(users.getContent());
        pagination.setDraw(userParam.getDraw());
        pagination.setRecordsFiltered((int) users.getTotalElements());
        pagination.setRecordsTotal((int) users.getTotalElements());
        if(users.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
   /*      model.addAttribute("userList", users);
       model.addAttribute("roleList", usersService.listAllRole());
        model.addAttribute("plazaList", baseInformationService.listAllPlaza());
        model.addAttribute("param", userParam);*/
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
    public String checkPassword(String oldPassword) throws UnsupportedEncodingException {
        Preconditions.checkNotNull(oldPassword, "oldPassword 不能为空");
        Users currentUser = getCurrentUser();
        return String.valueOf(StringUtils.endsWithIgnoreCase(currentUser.getPassword(), MD5.GetMD5Code(oldPassword)));
    }

    @ResponseBody
    @RequestMapping(value = "changePassword")
    public String changePassword(String oldPassword, String password) throws UnsupportedEncodingException {
        Preconditions.checkNotNull(password, "password 不能为空");
        Users currentUser = getCurrentUser();
        if (StringUtils.equalsIgnoreCase( MD5.GetMD5Code(oldPassword), currentUser.getPassword())) {
            currentUser.setPassword( MD5.GetMD5Code(password));
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
   /*     Users currentUser = getCurrentUser();
        if(null!=currentUser){
            sysPlaza.setPlaUserId(String.valueOf(currentUser.getId()));
            sysPlaza.setPlaUserName(currentUser.getUserName());
            sysPlaza.setPlaUserNo(currentUser.getUserNo());
        }
        sysPlaza.setPlaModifyTime(new Date());
        sysPlaza.setPlaInUse("0");

        SysPlaza save = baseInformationService.saveOrUpdateSysPlaza(sysPlaza);
*/
        Users currentUser = getCurrentUser();
        long id = 0;
        if (sysPlaza.getPlaNo() != null) {
            id = sysPlaza.getPlaNo();
            SysPlaza oldSysPlaza = baseInformationService.findSysPlaza(sysPlaza.getPlaNo());
            oldSysPlaza.setPlaNo(sysPlaza.getPlaNo());
            oldSysPlaza.setPlaName(sysPlaza.getPlaName());
            oldSysPlaza.setPlaLinkMan(sysPlaza.getPlaLinkMan());
            oldSysPlaza.setPlaRemark(sysPlaza.getPlaRemark());
            oldSysPlaza.setPlaPhone(sysPlaza.getPlaPhone());
            oldSysPlaza.setPlaFax(sysPlaza.getPlaFax());
            oldSysPlaza.setPlaAddress(sysPlaza.getPlaAddress());
            oldSysPlaza.setPlaZipCode(sysPlaza.getPlaZipCode());
            oldSysPlaza.setPlaModifyTime(new Date());
            SysPlaza save = baseInformationService.saveOrUpdateSysPlaza(oldSysPlaza,currentUser);
        } else {
            if(null!=currentUser){
                sysPlaza.setPlaUserId(String.valueOf(currentUser.getId()));
                sysPlaza.setPlaUserName(currentUser.getUserName());
                sysPlaza.setPlaUserNo(currentUser.getUserNo());
            }
            sysPlaza.setPlaModifyTime(new Date());
            SysPlaza save = baseInformationService.saveOrUpdateSysPlaza(sysPlaza, currentUser);
            id = save.getPlaNo();
        }

        return AjaxResponseBodyFactory.createSuccessBody(true, id);
    }

    @ResponseBody
    @RequestMapping(value = "sysPlazaNotExist")
    public String sysPlazaNotExist(Long plaNo) {
        Preconditions.checkNotNull(plaNo, "plazaNo 不能为空");
        SysPlaza sysPlaza = baseInformationService.findSysPlaza(plaNo);
        return sysPlaza == null ? "true" : "false";
    }
    @RequestMapping(value = "/plazalist")
    public String plazalist(UserParam userParam, Model model) {
     /*   List<SysPlaza> sysPlazas = baseInformationService.sysPlazaListAll();
        model.addAttribute("sysPlazaList", sysPlazas);*/
        //model.addAttribute("roleList", usersService.listAllRole());
        //model.addAttribute("param", userParam);
        return "cardmaintenanceadmin/users/plaza-list";
    }

    @ResponseBody
    @RequestMapping(value = "/plazaPage")
    public String plazaPage(UserParam userParam) {
        int pageNo = userParam.getStart() / userParam.getLength() + 1;
        Pagination<SysPlaza> pagination = new Pagination<SysPlaza>();
        Page<SysPlaza> sysPlazas = baseInformationService.sysPlazaListAllPage(pageNo, userParam.getLength());
        pagination.setData(sysPlazas.getContent());
        pagination.setDraw(userParam.getDraw());
        pagination.setRecordsFiltered((int) sysPlazas.getTotalElements());
        pagination.setRecordsTotal((int) sysPlazas.getTotalElements());
        if(sysPlazas.getContent().size()>0) {
            return JsonMapper.nonEmptyMapper().toJson(pagination);
        }else{
            return JsonMapper.nonDefaultMapper().toJson(pagination);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteplaza", method = RequestMethod.GET)
    public String deleteplaza(Long plazaNo) {
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
            long id = Long.parseLong(idStr);
            SysPlaza sysPlaza = baseInformationService.findSysPlaza(id);
            sysPlazaList.add(sysPlaza);
        }
        baseInformationService.delete(getCurrentUser(), sysPlazaList);
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/alterPlazainUse")
    public String alterPlazainUse(Long plazaNo,int inUse){
        Users currentuser= getCurrentUser();
        SysPlaza sysPlaza= baseInformationService.findSysPlaza(plazaNo);
        sysPlaza.setPlaInUse(inUse);
        SysPlaza save= baseInformationService.saveOrUpdateSysPlaza(sysPlaza,currentuser);
        return "ok";
    }


    @RequestMapping(value = "/baseinformationlist")
    public String baseinformationlist(SysConfig sysConfig, Model model) {
        List<SysConfig> sysConfigs = baseInformationService.SysConfigListAll();
        model.addAttribute("sysConfigList", sysConfigs);
        //model.addAttribute("roleList", usersService.listAllRole());
        //model.addAttribute("param", userParam);
        return "cardmaintenanceadmin/baseinformation-list";
    }


    @ResponseBody
    @RequestMapping(value = "/alertbaseinfo", method = RequestMethod.POST)
    public String alertbaseinfo(SysConfig sysConfig) {
        Long  id=0l;
        Users currentUser = getCurrentUser();
        if(null!=sysConfig.getCfConfigName()&&""!=sysConfig.getCfConfigName()){
            SysConfig oldSysConfig = baseInformationService.findSysConfigById(sysConfig.getCfConfigName());
            oldSysConfig.setCfConfigValue(sysConfig.getCfConfigValue());
            SysConfig save = baseInformationService.saveOrUpdateSysConfig(oldSysConfig,currentUser);
            id=2l;
        }

        return AjaxResponseBodyFactory.createSuccessBody(true, id);
    }
}
