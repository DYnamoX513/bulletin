package com.learn.bulletin.service;

import com.learn.bulletin.dao.RoleDao;
import com.learn.bulletin.entity.ResourceRole;
import com.learn.bulletin.entity.Role;
import com.learn.bulletin.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleDao {
    @Autowired
    private RoleDao roleDao;

    @Override
    public void insertRole(Role role) {
        roleDao.insertRole(role);
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public void updateRole(Long id) {

    }

    @Override
    public Role findRoleById(Long id) {
        return null;
    }

    @Override
    public List<Role> findAllRole() {
        return null;
    }

    @Override
    public List<Role> findByUserId(Long id) {
        return null;
    }

    @Override
    public void insertUserRoles(List<UserRole> userRoles) {

    }

    @Override
    public void deleteUserRole(Long userId) {

    }

    @Override
    public void insertRoleResources(List<ResourceRole> roleResources) {

    }

    @Override
    public void deleteRoleResources(Long roleId, List<Long> resourcesIds) {

    }
}
