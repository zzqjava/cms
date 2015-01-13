package com.qatang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chirowong on 2014/12/27.
 */
@Controller
@RequestMapping(value = "/error")
public class UnauthorizedController extends BaseController {
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        return "error/unauthorized";
    }
}
