package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.Exception.UserNameExisted;
import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateUserEntityReq;
import com.nthuy.healthinsurancemanager.dto.response.UpdateUserEntityRes;
import com.nthuy.healthinsurancemanager.repository.entity.UserEntity;
import com.nthuy.healthinsurancemanager.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/users")
    @ApiMessage("Tạo người dùng mới")
    public ResponseEntity<Long> createUserEntity(
            @Valid
            @RequestBody CreateUserEntityReq createUserEntityReq
    ) throws UserNameExisted {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleCreateUserEntity(createUserEntityReq));
    }

    @GetMapping("/users")
    @ApiMessage("Lấy danh sách người dùng")
    public ResponseEntity<ResultPaginationDTO> getAllUserEntities(
            @Filter Specification<UserEntity> spec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(this.userService.handleGetAllUserEntities(spec, pageable));
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage("Xoá người dùng")
    public ResponseEntity<String> deleteUserEntity(
            @PathVariable long id
    ) throws IdInvalidException {

        boolean idExists = this.userService.handleCheckIdExists(id);

        if (!idExists) {
            throw new IdInvalidException("ID " + id + " không tồn tại");
        }
        this.userService.handleDeleteUserEntity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Xoá người dùng thành công");
    }
    @PutMapping("/users/{id}")
    @ApiMessage("Cập nhật thông tin người dùng")
    public ResponseEntity<UpdateUserEntityRes> updateUserEntity(
            @PathVariable long id,
            @Valid
            @RequestBody UpdateUserEntityReq userEntityReq
    ) throws IdInvalidException {
        boolean idExists = this.userService.handleCheckIdExists(id);
        if (!idExists) {
            throw new IdInvalidException("ID " + id + " không tồn tại");
        }
        UserEntity userSave = this.userService.handleUpdateUserEntity(id, userEntityReq);
        return ResponseEntity.ok(this.userService.handleConvertToUpdateUserEntityRes(userSave));
    }

}
