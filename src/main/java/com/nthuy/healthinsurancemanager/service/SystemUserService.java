package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.request.CreateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.response.GetSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.RoleRepository;
import com.nthuy.healthinsurancemanager.repository.SystemUserRepository;


import com.nthuy.healthinsurancemanager.repository.entity.RoleEntity;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    SystemUserService {

    private final SystemUserRepository systemUserRepository;
    private final RoleRepository roleRepository;

    public SystemUserService(SystemUserRepository systemUserRepository, RoleRepository roleRepository) {
        this.systemUserRepository = systemUserRepository;
        this.roleRepository = roleRepository;
    }

    public Long handleCreateUser(CreateSystemUserRequest sysUser) {
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(sysUser.getEmail());
        systemUser.setUserName(sysUser.getUserName());
        systemUser.setFullName(sysUser.getFullName());
        systemUser.setGender(sysUser.getGender());
        systemUser.setPassWord(sysUser.getPassWord());
        systemUser.setAddress(sysUser.getAddress());
        systemUser.setPhone(sysUser.getPhone());
        Optional<RoleEntity> role = this.roleRepository.findById(sysUser.getRoleID());
        if (role.isPresent()) {
            RoleEntity roleEntity = role.get();
            systemUser.setRole(roleEntity);
        }
        systemUser.setDateOfBirth(sysUser.getDateOfBirth());
        systemUser.setIdCardNumber(sysUser.getIdCardNumber());
        systemUser = systemUserRepository.save(systemUser);
        return systemUser.getId();
    }

    public List<GetSystemUserResponse> handleGetAllSystemUsers() {
        List<SystemUser> systemUsers = this.systemUserRepository.findAll();
        return systemUsers.stream()
                .map(user -> new GetSystemUserResponse(
                        user.getId(),
                        user.getUserName(),
                        user.getDateOfBirth(),
                        user.getFullName(),
                        user.getPhone(),
                        user.getEmail()
                ))
                .toList();
    }
    public SystemUser handleGetUserByUsername (String username) {
        Optional<SystemUser> optionalSystemUser  = this.systemUserRepository.findByUserName(username);
        if (optionalSystemUser.isPresent()) {
            return optionalSystemUser.get();
        } else {
            return null;
        }
    }

}
