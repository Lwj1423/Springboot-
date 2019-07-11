package com.lwj.projectsecurity.config;

import com.lwj.projectsecurity.entity.SysPermission;
import com.lwj.projectsecurity.service.SysPermissionService;
import com.lwj.projectsecurity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 自定义用户权限
 */
@Configuration
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysRoleService sysRoleService;

    //普通的targetDomainObject判断
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        //获得loadUserByUsername()方法的用户
        User user = (User) authentication.getPrincipal();
        //获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        //遍历用户所有的角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Integer roleId = sysRoleService.selectByName(roleName).getId();
            //得到角色所有的权限
            List<SysPermission> permissionList = permissionService.listByRoleId(roleId);

            //遍历permissonList
            for (SysPermission syspermission : permissionList) {
                //获取权限集合
                List permissions = syspermission.getPermissions();
                //如果访问的url和权限用户符合的话，返回true
                if (targetUrl.equals(syspermission.getUrl()) && permissions.contains(targetPermission))
                    return true;
            }
        }

        return false;
    }

    //用于ACL的访问控制
    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
