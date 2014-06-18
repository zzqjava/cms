package com.qatang.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class CssController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(String username, String password, String captcha, ModelMap model) {
        logger.info(username);
        model.addAttribute("captcha", captcha);
        return "list";
    }
}
