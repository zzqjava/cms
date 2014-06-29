package com.qatang.cms.controller.user;

import com.qatang.cms.controller.BaseController;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by JSH on 2014/6/26.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap) {
        List<User> userList = userService.getList();
        modelMap.addAttribute(userList);
        return "user/userList";
    }

    @RequestMapping(value = "/input")
    public String input(Long id, ModelMap modelMap) {
        if (id != null) {
            User user = userService.get(id);
            modelMap.addAttribute("user", user);
        }
        return "user/userInput";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserForm userForm) {
        return "success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long id, UserForm userForm) {
        return "success";
    }

    @RequestMapping(value = "/del")
    public String delete(Long id, ModelMap modelMap) {
        if (id == null) {
            logger.error("删除用户，id为空");
            modelMap.addAttribute(ERROR_MESSAGE_KEY, "删除用户，id为空");
            return "failure";
        }
        userService.delete(id);
        return "redirect:/user/list";
    }
}
