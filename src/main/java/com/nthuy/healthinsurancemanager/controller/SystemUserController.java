package com.nthuy.healthinsurancemanager.controller;


import com.nthuy.healthinsurancemanager.Exception.IdInvalidException;
import com.nthuy.healthinsurancemanager.Exception.UserNameExisted;
import com.nthuy.healthinsurancemanager.dto.request.CreateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.request.ResultPaginationDTO;
import com.nthuy.healthinsurancemanager.dto.request.UpdateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.response.UpdateSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import com.nthuy.healthinsurancemanager.service.SystemUserService;

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
public class SystemUserController {

    private final SystemUserService sysUserService;
    private final PasswordEncoder passwordEncoder;

    public SystemUserController(SystemUserService sysUserService, PasswordEncoder passwordEncoder) {
        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/sys_users")
    @ApiMessage("Tạo người dùng hệ thống mới")
    public ResponseEntity<String> createSystemUser(
            @Valid
            @RequestBody CreateSystemUserRequest createSystemUserRequest
    ) throws UserNameExisted {
        boolean userNameExists = this.sysUserService.userNameExists(createSystemUserRequest.getUserName());
        if (userNameExists) {
            throw new UserNameExisted("Username " +
                    createSystemUserRequest.getUserName() + " đã tồn tại");
        }
        createSystemUserRequest.setPassWord(passwordEncoder.encode(createSystemUserRequest.getPassWord()));
        return ResponseEntity.status(HttpStatus.CREATED).body("User " + this.sysUserService.handleCreateUser(createSystemUserRequest) + " created successfully");
    }

    @GetMapping("/sys_users")
    @ApiMessage("Lấy danh sách tất cả người dùng hệ thống")
    public ResponseEntity<ResultPaginationDTO> getAllSystemUsers(
            @Filter Specification<SystemUserEntity> spec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(this.sysUserService.handleGetAllSystemUsers(spec, pageable));
    }

    @PutMapping("/sys_users/{id}")
    @ApiMessage("Cập nhật thông tin người dùng hệ thống")
    public ResponseEntity<UpdateSystemUserResponse> updateSystemUser(
            @PathVariable Long id,
            @Valid
            @RequestBody UpdateSystemUserRequest updateRequest) throws IdInvalidException {
        boolean idExists = this.sysUserService.idExists(id);
        if (!idExists) {
            throw new IdInvalidException("ID " + id + " không tồn tại");
        }
        SystemUserEntity updatedUser = this.sysUserService.handleUpdateSystemUser(id, updateRequest);
        return ResponseEntity.ok().body(this.sysUserService.convertToUpdateSystemUserResponse(updatedUser));
    }

    @DeleteMapping("/sys_users/{id}")
    @ApiMessage("Xóa người dùng hệ thống")
    public ResponseEntity<String> deleteSystemUser(@PathVariable Long id) throws IdInvalidException {
        boolean idExists = this.sysUserService.idExists(id);
        if (!idExists) {
            throw new IdInvalidException("ID " + id + " không tồn tại");
        }
        this.sysUserService.handleDeleteSystemUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User with ID " + id + " deleted successfully");
    }
}
