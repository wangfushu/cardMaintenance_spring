package gmms.controller;

import gmms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017-07-23.
 */
@RequestMapping("/login")
@Controller
public class LoginControl extends BaseControl {
    @Autowired
    private UsersService usersService;

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

}
