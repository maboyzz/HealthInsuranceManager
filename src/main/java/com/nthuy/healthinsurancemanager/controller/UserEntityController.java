package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.Exception.IdInvalidEx;
import com.nthuy.healthinsurancemanager.Exception.UserNameExist;
import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.response.UpdateUserEntityRes;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import com.nthuy.healthinsurancemanager.service.UserEntityService;
import com.nthuy.healthinsurancemanager.until.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEntityController {

    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserEntityController(UserEntityService userEntityService, PasswordEncoder passwordEncoder) {
        this.userEntityService = userEntityService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/users")
    @ApiMessage("Tạo người dùng mới")
    public ResponseEntity<Long> createUserEntity(
            @Valid
            @RequestBody CreateUserEntityReq createUserEntityReq
    ) throws UserNameExist {
        boolean userNameExists = this.userEntityService.handleCheckUserNameExists(createUserEntityReq.getUserName());
        if (userNameExists) {
            throw new UserNameExist("Username " + createUserEntityReq.getUserName() + " đã tồn tại");
        }
        createUserEntityReq.setPassWord(passwordEncoder.encode(createUserEntityReq.getPassWord()));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userEntityService.handleCreateUserEntity(createUserEntityReq));
    }

    @GetMapping("/users")
    @ApiMessage("Lấy danh sách người dùng")
    public ResponseEntity<ResultPaginationDTO> getAllUserEntities(
            @Filter Specification<UserEntity> spec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(this.userEntityService.handleGetAllUserEntities(spec, pageable));
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage("Xoá người dùng")
    public ResponseEntity<String> deleteUserEntity(
            @PathVariable long id
    ) throws IdInvalidEx {

        boolean idExists = this.userEntityService.handleCheckIdExists(id);

        if (!idExists) {
            throw new IdInvalidEx("ID " + id + " không tồn tại");
        }
        this.userEntityService.handleDeleteUserEntity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xoá người dùng thành công");
    }
    @PutMapping("/users/{id}")
    @ApiMessage("Cập nhật thông tin người dùng")
    public ResponseEntity<UpdateUserEntityRes> updateUserEntity(
            @PathVariable long id,
            @Valid
            @RequestBody UpdateUserEntityReq userEntityReq
    ) throws IdInvalidEx {
        boolean idExists = this.userEntityService.handleCheckIdExists(id);
        if (!idExists) {
            throw new IdInvalidEx("ID " + id + " không tồn tại");
        }
        UserEntity userSave = this.userEntityService.handleUpdateUserEntity(id, userEntityReq);
        return ResponseEntity.ok(this.userEntityService.handleConvertToUpdateUserEntityRes(userSave));
    }

}
