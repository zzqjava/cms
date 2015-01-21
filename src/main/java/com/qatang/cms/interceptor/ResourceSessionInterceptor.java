package com.qatang.cms.interceptor;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qatang on 14-6-27.
 */
public class ResourceSessionInterceptor extends HandlerInterceptorAdapter {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("开始获取session信息。");
        List<Resource> list = (List<Resource>) request.getSession().getAttribute(CommonConstants.RESOURCES_SESSION_KEY);
        request.setAttribute("resourceListForMenu", list);
        return true;
    }
}
