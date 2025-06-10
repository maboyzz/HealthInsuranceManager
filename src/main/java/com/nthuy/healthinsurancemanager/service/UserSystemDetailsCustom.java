package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component("userDetailsService")
public class UserSystemDetailsCustom implements UserDetailsService {


    private final SystemUserService systemUserService;

    public UserSystemDetailsCustom(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser sysUser = this.systemUserService.handleGetUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("username/password không hợp lệ");
        }
        return new User(
                sysUser.getUserName(),
                sysUser.getPassWord(),
                Collections.singletonList(new SimpleGrantedAuthority(sysUser.getRole().getName()))
        );
    }
}
