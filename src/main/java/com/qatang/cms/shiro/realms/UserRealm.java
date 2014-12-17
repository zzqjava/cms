package com.qatang.cms.shiro.realms;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.shiro.authentication.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qatang on 14/11/30.
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(username));
//        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        Set<String> stringPermissionSet = new HashSet<String>();
        stringPermissionSet.add("sys:user");
        authorizationInfo.setStringPermissions(stringPermissionSet);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();

        User user = userService.getByUsername(username);

        if(user == null) {
            throw new UnknownAccountException("帐号或密码错误！");//没找到帐号
        }

        if(EnableDisableStatus.ENABLE != user.getValid()) {
            throw new LockedAccountException("账号未激活！");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(passwordHelper.getSalt(user)),
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
