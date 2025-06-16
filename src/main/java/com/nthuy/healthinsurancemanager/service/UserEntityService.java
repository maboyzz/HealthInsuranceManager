package com.nthuy.healthinsurancemanager.service;

import com.nthuy.healthinsurancemanager.dto.Meta;
import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.Role;
import com.nthuy.healthinsurancemanager.dto.response.GetEntityUsersRes;
import com.nthuy.healthinsurancemanager.repository.RoleRepository;
import com.nthuy.healthinsurancemanager.repository.UserEntityRepository;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public boolean handleCheckIdExists(long id) {
        return this.userEntityRepository.existsById(id);
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

    public ResultPaginationDTO handleGetAllUserEntities(
            Specification<UserEntity> spec,
            Pageable pageable
    ) {
        Page<UserEntity> userEntities = userEntityRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPage(userEntities.getTotalPages());
        meta.setTotal(userEntities.getTotalElements());
        resultPaginationDTO.setMeta(meta);

        List<GetEntityUsersRes> listUserEntitiesRes = userEntities.getContent().stream()
                .map(user -> new GetEntityUsersRes(
                        user.getId(),
                        user.getUserName(),
                        user.getFullName(),
                        user.getDateOfBirth(),
                        user.getPhone(),
                        user.getEmail()
                )).toList();
        resultPaginationDTO.setResults(listUserEntitiesRes);
        return resultPaginationDTO;
    }
    public void handleDeleteUserEntity(long id) {
        this.userEntityRepository.deleteById(id);
    }

}
