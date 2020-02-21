package com.learn.bulletin.dao;

import com.learn.bulletin.entity.ResourceRole;
import com.learn.bulletin.entity.Role;
import com.learn.bulletin.entity.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {
//    @Insert("INSERT INTO roles(role_id,code,name,description) VALUES(#{role_id},#{code},#{name},#{description})")
//    void insertRole(Role role);
//
//    @Delete("DELETE FROM roles WHERE role_id = #{role_id}")
//    void deleteRole(@Param("role_id") Long id);
//
//    @Update("UPDATE roles SET code=#{code},name=#{name},description=#{description} WHERE role_id =#{role_id}")
//    void updateRole(@Param("role_id") Long id);
//
//    @Select("SELECT * FROM roles WHERE role_id=#{role_id}")
//    Role findRoleById(@Param("role_id") Long id);
//
//    @Select("SELECT * FROM roles")
//    List<Role> findAllRole();

    @Select("SELECT * FROM sys_app A" +
            "        LEFT JOIN oauth_client_details CD ON CD.client_id=A.client_id" +
            "        WHERE A.id=#{id}")
    List<Role> findByUserId(Long id);

//    void insertUserRoles(@Param("userRoles") List<UserRole> userRoles);
//
//    @Delete("DELETE FROM sys_user_role WHERE userId = #{userId}")
//    void deleteUserRole(Long userId);
//
//    void insertRoleResources(@Param("roleResources") List<ResourceRole> roleResources);
//
//    void deleteRoleResources(@Param("roleId") Long roleId, @Param("resourcesIds") List<Long> resourcesIds);
}
