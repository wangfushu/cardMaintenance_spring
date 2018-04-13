package gmms.controller;

import gmms.domain.db.SysConfig;
import gmms.domain.db.Users;
import gmms.service.BaseInformationService;
import gmms.service.UsersService;
import gmms.util.CommonExceptions.InvalidException;
import gmms.util.StringUtils;
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
    private BaseInformationService baseInformationService;

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

//    @ResponseBody
//    @RequestMapping(value = "")
//    public String login(String userName, String password) {
//        Users users = usersService.findByName(userName);
//        if (null == users) {
//            return "false";
//        } else {
//            return (users.getUserName().equals(userName) && users.getPassword().equals(password)) ? "true" : "false";
//        }
//    }



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
            return "success";
        } catch (NumberFormatException e) {
            return "error";
        } catch (InvalidException e) {
            //String result=e.getMessage();
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
