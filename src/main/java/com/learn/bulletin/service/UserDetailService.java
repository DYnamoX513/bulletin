package com.learn.bulletin.service;

import com.learn.bulletin.entity.Resource;
import com.learn.bulletin.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //TODO:
        com.learn.bulletin.entity.User user = userService.getUserByName(s).get(0);

        Set<GrantedAuthority> authorities = new HashSet<>();
        List<Role> roles = roleService.findByUserId(user.getUser_id());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        List<Resource> resources = resourceService.findByUserId(user.getUser_id());
        for (Resource resource : resources){
            authorities.add(new SimpleGrantedAuthority(resource.getCode()));
        }

        return new User(s,user.getPassword(),authorities);
    }
}
