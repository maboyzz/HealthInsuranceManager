package com.nthuy.healthinsurancemanager.controller;



import com.nthuy.healthinsurancemanager.dto.request.CreateSystemUserRequest;
import com.nthuy.healthinsurancemanager.dto.response.GetSystemUserResponse;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUser;
import com.nthuy.healthinsurancemanager.service.SystemUserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUserController {

    private final SystemUserService sysUserService;

    public SystemUserController(SystemUserService sysUserService) {
        this.sysUserService = sysUserService;
    }


    @PostMapping("/users")
    public ResponseEntity<String> createSystemUser(

            @RequestBody CreateSystemUserRequest createSystemUserRequestsysUser
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body("User "+this.sysUserService.handleCreateUser(createSystemUserRequestsysUser)+" created successfully");
    }
    @GetMapping("/users")
    public ResponseEntity<List<GetSystemUserResponse>> getAllSystemUsers() {

        return ResponseEntity.ok(this.sysUserService.handleGetAllSystemUsers());
    }
}
