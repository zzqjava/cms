package com.qatang.cms.shiro.realms;

import com.qatang.cms.entity.resource.Resource;
import com.qatang.cms.entity.role.Role;
import com.qatang.cms.entity.role.RoleResource;
import com.qatang.cms.entity.user.User;
import com.qatang.cms.entity.user.UserRole;
import com.qatang.cms.enums.EnableDisableStatus;
import com.qatang.cms.service.resource.ResourceService;
import com.qatang.cms.service.role.RoleResourceService;
import com.qatang.cms.service.role.RoleService;
import com.qatang.cms.service.user.UserRoleService;
import com.qatang.cms.service.user.UserService;
import com.qatang.cms.shiro.authentication.PasswordHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qatang on 14/11/30.
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<UserRole> userRoles = userRoleService.findUserRolesByUserId(user.getId());
        List<Role> roleList = new ArrayList<>();
        for(UserRole ur : userRoles){
            roleList.add(roleService.getRole(ur.getId()));
        }

        Set<String> roleStrs = new HashSet<>();
        List<RoleResource> roleResourceList = new ArrayList<>();
        for(Role role : roleList){
            roleStrs.add(role.getIdentifier());
            roleResourceList.addAll(roleResourceService.findByRoleId(role.getId()));
        }
        authorizationInfo.setRoles(roleStrs);

        List<Resource> resources = new ArrayList<>();
        for(RoleResource rr : roleResourceList){
            resources.add(resourceService.get(rr.getResourceId()));
        }
        Set<String> resourceStrs = new HashSet<>();
        for(Resource resource : resources){
            if(!StringUtils.isEmpty(resource.getIdentifier())){
                resourceStrs.add(resource.getIdentifier());
            }
        }
        authorizationInfo.setStringPermissions(resourceStrs);
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
