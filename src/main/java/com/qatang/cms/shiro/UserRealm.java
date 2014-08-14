package com.qatang.cms.shiro;

import com.qatang.cms.entity.user.User;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.service.impl.user.UserServiceImpl;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.utils.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by chirowong on 14-8-6.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();

        User user = userService.getByUsername(username);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(user.getValid().getValue() == EnableDisableStatus.DISABLE.getValue()) {
            throw new UnknownAccountException(); //帐号无效
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        byte[] salt = Encodes.decodeHex(user.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(salt),
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserServiceImpl.HASH_ALGORITHM);
        matcher.setHashIterations(UserServiceImpl.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
