package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.Meta;
import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.response.GetEntityUsersRes;
import com.nthuy.healthinsurancemanager.dto.response.UpdateUserEntityRes;
import com.nthuy.healthinsurancemanager.repository.RoleRepository;
import com.nthuy.healthinsurancemanager.repository.UserRepository;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public boolean handleCheckUserNameExists(String userName) {
        return this.userRepository.existsByUserName(userName);
    }
    public boolean handleCheckIdExists(long id) {
        return this.userRepository.existsById(id);
    }
    public UpdateUserEntityRes handleConvertToUpdateUserEntityRes(UserEntity userEntity) {
        UpdateUserEntityRes updateUserEntityRes = new UpdateUserEntityRes();
        updateUserEntityRes.setFullName(userEntity.getFullName());
        updateUserEntityRes.setDateOfBirth(userEntity.getDateOfBirth());
        updateUserEntityRes.setGender(userEntity.getGender());
        updateUserEntityRes.setJob(userEntity.getJob());
        updateUserEntityRes.setIdCardNumber(userEntity.getIdCardNumber());
        updateUserEntityRes.setPhone(userEntity.getPhone());
        updateUserEntityRes.setAddress(userEntity.getAddress());
        return updateUserEntityRes;
    }
    public long handleCreateUserEntity(CreateUserEntityReq createUserEntityReq) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(createUserEntityReq.getFullName());
        userEntity.setDateOfBirth(createUserEntityReq.getDateOfBirth());
        userEntity.setGender(createUserEntityReq.getGender());
        userEntity.setJob(createUserEntityReq.getJob());
        userEntity.setIdCardNumber(createUserEntityReq.getIdCardNumber());
        userEntity.setPhone(createUserEntityReq.getPhone());
        userEntity.setEmail(createUserEntityReq.getEmail());
        userEntity.setAddress(createUserEntityReq.getAddress());
        userEntity.setRole(this.roleRepository.findById(2).orElse(null));
        userEntity = userRepository.save(userEntity);
        return userEntity.getId();
    }
    public UserEntity handleUpdateUserEntity (long id, UpdateUserEntityReq userEntityReq){
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity existingUserEntity = userEntityOptional.get();
            existingUserEntity.setFullName(userEntityReq.getFullName());
            existingUserEntity.setDateOfBirth(userEntityReq.getDateOfBirth());
            existingUserEntity.setGender(userEntityReq.getGender());
            existingUserEntity.setJob(userEntityReq.getJob());
            existingUserEntity.setIdCardNumber(userEntityReq.getIdCardNumber());
            existingUserEntity.setPhone(userEntityReq.getPhone());
            existingUserEntity.setAddress(userEntityReq.getAddress());
            return this.userRepository.save(existingUserEntity);
        }
        return null;
    }


    public ResultPaginationDTO handleGetAllUserEntities(
            Specification<UserEntity> spec,
            Pageable pageable
    ) {
        Page<UserEntity> userEntities = userRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPage(userEntities.getTotalPages());
        meta.setTotal(userEntities.getTotalElements());
        resultPaginationDTO.setMeta(meta);

        List<GetEntityUsersRes> listUserEntitiesRes = userEntities.getContent().stream()
                .map(user -> new GetEntityUsersRes(
                        user.getFullName(),
                        user.getIdCardNumber(),
                        user.getDateOfBirth(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getRole().getName()
                )).toList();
        resultPaginationDTO.setResults(listUserEntitiesRes);
        return resultPaginationDTO;
    }
    public void handleDeleteUserEntity(long id) {
        this.userRepository.deleteById(id);
    }



}
