package gmms.controller;

import gmms.domain.db.SysConfig;
import gmms.domain.db.TmTagStore;
import gmms.domain.db.Users;
import gmms.service.BaseInformationService;
import gmms.service.TagService;
import gmms.service.UsersService;
import gmms.util.CommonExceptions.InvalidException;
import gmms.util.JsonMapper;
import gmms.util.MD5;
import gmms.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017-07-23.
 */
@RequestMapping("/login")
@Controller
public class LoginControl extends BaseControl {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BaseInformationService baseInformationService;
    private Logger LOGGER = LoggerFactory.getLogger(LoginControl.class);
    @RequestMapping(value = "")
    public String login(String msg, Model model) {
        System.out.println("msg=" + msg);
        model.addAttribute("msg", msg);
        return "cardmaintenanceadmin/login";
    }

    @RequestMapping(value = "/test")
    public String test(String msg, Model model) {
        return "cardmaintenanceadmin/index";
    }

    @ResponseBody
    @RequestMapping(value = "login")
    public String applogin(String userName, String password) throws UnsupportedEncodingException {
        Users users = usersService.findByName(userName);
        if (null == users ) {
            LOGGER.info("用户:" + userName + " 密码： " + password + "   ----无此用户");
            return "6";
        } else if (!users.getIsUser()){

            LOGGER.info("用户:" + userName + " 密码： " + password + "用户权限 "+users.getIsUser()+"  ----用户无权限");
            return "7";
             }else{

                if ("true".equals((users.getUserNo().equalsIgnoreCase(userName) && users.getPassword().endsWith(MD5.GetMD5Code(password))) ? "true" : "false")){

                    return JsonMapper.nonEmptyMapper().toJson(users);
            }else{
                    LOGGER.info("用户:" + userName + " 密码： " + password + "---------密码错误");
                    return "2";
                }

        }
    }



    @ResponseBody
    @RequestMapping(value = "changePassWord", method = RequestMethod.POST)
    public String changePassWord(Long eId, String oldpassword,String newpassword) throws UnsupportedEncodingException {
        try {
            if(null!=eId && StringUtils.isNotNullBlank(String.valueOf(eId))){
            }else{
                Users users=getCurrentUser();
                eId=users.getId();
            }
            this.usersService.changePassword(eId, oldpassword, newpassword);
            return "ok";
        } catch (NumberFormatException e) {
            return "error";
        } catch (InvalidException e) {
            return e.getMessage();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/superPassword")
    public String superPassword(String password) throws UnsupportedEncodingException {
        SysConfig sysConfig =baseInformationService.findSuperAdminPassWordByValue();
        if (null == sysConfig) {
            return "false";
        } else {
            if(sysConfig.getCfConfigValue().equals(password))	{
                return "true";
            }	else{
                return "false";
            }
        }
    }



}
