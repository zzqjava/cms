package com.qatang.cms.service.impl.role;

import com.qatang.cms.dao.role.RoleResourceDao;
import com.qatang.cms.entity.role.RoleResource;
import com.qatang.cms.service.role.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by zhangzq on 2014/6/24.
 */
@Service
@Transactional
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public void save(Long roleId, List<RoleResource> roleResources){
        List<RoleResource> existList = roleResourceDao.findByRoleId(roleId);
        for(RoleResource roleResource : existList){
            roleResourceDao.delete(roleResource);
        }

        for(RoleResource roleResource : roleResources){
            roleResourceDao.save(roleResource);
        }
    }

    @Override
    public List<RoleResource> findByRoleId(Long roleId){
        return roleResourceDao.findByRoleId(roleId);
    }
}
