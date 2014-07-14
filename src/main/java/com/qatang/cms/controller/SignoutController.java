package com.qatang.cms.controller;

import com.qatang.cms.constants.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qatang on 14-6-5.
 */
@Controller
public class SignoutController extends BaseController {
    @RequestMapping("/signout")
    public String dashboard(HttpServletRequest request) {
        logger.info("退出登录");
        if (request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY) != null) {
            request.getSession().removeAttribute(CommonConstants.USER_SESSION_KEY);
        }
        return "redirect:/signin";
    }
}
