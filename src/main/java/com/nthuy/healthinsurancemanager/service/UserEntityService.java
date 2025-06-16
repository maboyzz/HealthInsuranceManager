package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.request.Role;
import com.nthuy.healthinsurancemanager.repository.RoleRepository;
import com.nthuy.healthinsurancemanager.repository.UserEntityRepository;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;

    public UserEntityService(UserEntityRepository userEntityRepository, RoleRepository roleRepository) {
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
    }


    public boolean handleCheckUserNameExists(String userName) {
        return this.userEntityRepository.existsByUserName(userName);
    }
    public long handleCreateUserEntity(CreateUserEntityReq createUserEntityReq) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(createUserEntityReq.getUserName());
        userEntity.setFullName(createUserEntityReq.getFullName());
        userEntity.setDateOfBirth(createUserEntityReq.getDateOfBirth());
        userEntity.setGender(createUserEntityReq.getGender());
        userEntity.setJob(createUserEntityReq.getJob());
        userEntity.setIdCardNumber(createUserEntityReq.getIdCardNumber());
        userEntity.setPassWord(createUserEntityReq.getPassWord());
        userEntity.setPhone(createUserEntityReq.getPhone());
        userEntity.setEmail(createUserEntityReq.getEmail());
        userEntity.setAddress(createUserEntityReq.getAddress());
        userEntity.setRole(this.roleRepository.findById(2).orElse(null));
        userEntity = userEntityRepository.save(userEntity);
        return userEntity.getId();
    }
}
