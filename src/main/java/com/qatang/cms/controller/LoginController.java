package com.qatang.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, String captcha, ModelMap model) {
        logger.info(username);
        logger.info(password);
        logger.info(captcha);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("captcha", captcha);
        return "login";
    }
}
