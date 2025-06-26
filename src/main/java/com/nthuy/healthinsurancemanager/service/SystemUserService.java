package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.Meta;
import com.nthuy.healthinsurancemanager.dto.request.CreateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.response.UpdateSystemUserResponse;
import com.nthuy.healthinsurancemanager.dto.response.GetSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.RoleRepository;
import com.nthuy.healthinsurancemanager.repository.SystemUserRepository;


import com.nthuy.healthinsurancemanager.repository.entity.RoleEntity;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserService {

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

    public SystemUserEntity handleGetUserByUsername(String userName) {
        return this.systemUserRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User with username " + userName + " does not exist"));
    }

    public Long handleCreateUser(CreateSystemUserRequest sysUser) {
        SystemUserEntity systemUserEntity = new SystemUserEntity();
        systemUserEntity.setEmail(sysUser.getEmail());
        systemUserEntity.setUserName(sysUser.getUserName());
        systemUserEntity.setFullName(sysUser.getFullName());
        systemUserEntity.setGender(sysUser.getGender());
        systemUserEntity.setPassWord(sysUser.getPassWord());
        systemUserEntity.setAddress(sysUser.getAddress());
        systemUserEntity.setPhone(sysUser.getPhone());
        Optional<RoleEntity> role = this.roleRepository.findById(sysUser.getRoleID());
        if (role.isPresent()) {
            RoleEntity roleEntity = role.get();
            systemUserEntity.setRole(roleEntity);
        }
        systemUserEntity.setDateOfBirth(sysUser.getDateOfBirth());
        systemUserEntity.setIdCardNumber(sysUser.getIdCardNumber());
        systemUserEntity = systemUserRepository.save(systemUserEntity);
        return systemUserEntity.getId();
    }

    public ResultPaginationDTO handleGetAllSystemUsers(Specification<SystemUserEntity> spec, Pageable pageable) {
        Page<SystemUserEntity> page = systemUserRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPage(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        resultPaginationDTO.setMeta(meta);

        List<GetSystemUserResponse> systemUsers = page.getContent().stream()
                .map(user -> new GetSystemUserResponse(
                        user.getId(),
                        user.getUserName(),
                        user.getDateOfBirth(),
                        user.getFullName(),
                        user.getPhone(),
                        user.getEmail()
                ))
                .toList();
        resultPaginationDTO.setResults(systemUsers);
        return resultPaginationDTO;
    }

    public SystemUserEntity handleUpdateSystemUser(Long id, UpdateSystemUserRequest updateRequest) {
        Optional<SystemUserEntity> userOpt = this.systemUserRepository.findById(id);
        if (userOpt.isPresent()) {
            SystemUserEntity user = userOpt.get();
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



    public UpdateSystemUserResponse convertToUpdateSystemUserResponse(SystemUserEntity systemUserEntity) {
        UpdateSystemUserResponse updateSystemUserResponse = new UpdateSystemUserResponse();
        updateSystemUserResponse.setFullName(systemUserEntity.getFullName());
        updateSystemUserResponse.setGender(systemUserEntity.getGender());
        updateSystemUserResponse.setAddress(systemUserEntity.getAddress());
        updateSystemUserResponse.setPhone(systemUserEntity.getPhone());
        updateSystemUserResponse.setDateOfBirth(systemUserEntity.getDateOfBirth());
        updateSystemUserResponse.setIdCardNumber(systemUserEntity.getIdCardNumber());
        return updateSystemUserResponse;
    }

}
