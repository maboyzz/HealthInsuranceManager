package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.request.CreateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.request.UpdateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.response.UpdateSystemUserResponse;
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

    public boolean userNameExists(String userName) {
        return this.systemUserRepository.existsByUserName(userName);
    }
    public boolean idExists(Long id) {
        return this.systemUserRepository.existsById(id);
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
    public SystemUser handleUpdateSystemUser(Long id, UpdateSystemUserRequest updateRequest) {
        Optional<SystemUser> userOpt = this.systemUserRepository.findById(id);
        if (userOpt.isPresent()) {
            SystemUser user = userOpt.get();
            user.setFullName(updateRequest.getFullName());
            user.setGender(updateRequest.getGender());
            user.setAddress(updateRequest.getAddress());
            user.setIdCardNumber(updateRequest.getIdCardNumber());
            user.setDateOfBirth(updateRequest.getDateOfBirth());
            user.setPhone(updateRequest.getPhone());
            return this.systemUserRepository.save(user);
        } else {
            return null;
        }
    }

    public void handleDeleteSystemUser(Long id) {
        this.systemUserRepository.deleteById(id);
    }

    public SystemUser handleGetUserByUsername (String username) {
        Optional<SystemUser> optionalSystemUser  = this.systemUserRepository.findByUserName(username);
        return optionalSystemUser.orElse(null);
    }
    public UpdateSystemUserResponse convertToUpdateSystemUserResponse(SystemUser systemUser) {
        UpdateSystemUserResponse updateSystemUserResponse = new UpdateSystemUserResponse();
        updateSystemUserResponse.setFullName(systemUser.getFullName());
        updateSystemUserResponse.setGender(systemUser.getGender());
        updateSystemUserResponse.setAddress(systemUser.getAddress());
        updateSystemUserResponse.setPhone(systemUser.getPhone());
        updateSystemUserResponse.setDateOfBirth(systemUser.getDateOfBirth());
        updateSystemUserResponse.setIdCardNumber(systemUser.getIdCardNumber());
        return updateSystemUserResponse;
    }

}
