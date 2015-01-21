package com.qatang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * Created by chirowong on 2014/8/14.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }
}
