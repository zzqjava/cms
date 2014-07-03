package com.qatang.cms.interceptor;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qatang on 14-6-27.
 */
public class UserSessionInterceptor extends HandlerInterceptorAdapter {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("开始验证session信息。");
        logger.info("request url : " + request.getRequestURL());
        User user = (User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        if (user == null) {
            String msg = String.format("session超时，请重新登录");
            logger.info(msg);

            response.sendRedirect("/signin");

            return false;
        }

        return true;
    }
}
