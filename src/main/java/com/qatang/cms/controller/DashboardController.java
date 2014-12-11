package com.qatang.cms.controller;

import com.qatang.cms.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
public class DashboardController extends BaseController {
    @RequestMapping("/dashboard")
    public String dashboard(ModelMap modelMap) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        modelMap.addAttribute(user);
        return "/user/dashboard";
    }
}
