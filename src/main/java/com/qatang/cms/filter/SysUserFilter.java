package com.qatang.cms.filter;

import com.qatang.cms.constants.CommonConstants;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        User user = (User)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CommonConstants.CURRENT_USER, user);
        return true;
    }
}
