package com.qatang.cms;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.service.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by chirowong on 2014/9/3.
 */
public class CmsAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token){
        CmsAuthenticationToken cmsAuthenticationToken = (CmsAuthenticationToken)token;
        String username = cmsAuthenticationToken.getUsername();
        String password = new String(cmsAuthenticationToken.getPassword());
        String captcha = cmsAuthenticationToken.getCaptcha();

        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            User user = userService.getByUsername(username);
            if(user == null){
                throw new UnknownAccountException();
            }
            if(user.getValid().getValue() != EnableDisableStatus.ENABLE.getValue()){
                throw new DisabledAccountException();
            }
            if(!DigestUtils.md5Hex(password).equals(user.getPassword())){
                throw new IncorrectCredentialsException();
            }
            if(user.getLoginTime() != null){
                user.setLastLoginTime(user.getLoginTime());
            }
            user.setLoginTime(new Date());
            userService.update(user);
            return new SimpleAuthenticationInfo(username,password,user.getName());
        }
        throw new UnknownAccountException();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //补充方法
        //List<Role> roles = roleService.
        // authorizationInfo.setRoles(userService.findRoles(username));
        // authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }
}
