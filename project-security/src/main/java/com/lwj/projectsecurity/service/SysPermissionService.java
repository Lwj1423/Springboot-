package com.lwj.projectsecurity.service;

import com.lwj.projectsecurity.dao.SysPermissionMapper;
import com.lwj.projectsecurity.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     * @param roleId
     * @return
     */
    public List<SysPermission> listByRoleId(Integer roleId){
        return permissionMapper.listByRoleId(roleId);
    }
}
