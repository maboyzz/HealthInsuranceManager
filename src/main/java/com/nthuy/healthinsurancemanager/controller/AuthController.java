package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.dto.request.LoginDTO;
import com.nthuy.healthinsurancemanager.dto.response.ResLoginDTO;
import com.nthuy.healthinsurancemanager.repository.entity.SystemUserEntity;
import com.nthuy.healthinsurancemanager.service.SystemUserService;
import com.nthuy.healthinsurancemanager.until.SecurityUtil;
import com.nthuy.healthinsurancemanager.until.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final SystemUserService systemUserService;


    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil, SystemUserService systemUserService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.systemUserService = systemUserService;
    }

    @PostMapping("/login")
    @ApiMessage("Đăng nhập hệ thống")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        System.out.println("Login request: " + loginDTO.getUserName() + " - " + loginDTO.getPassWord());
        //Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassWord());
        //xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // create a token
        String access_token = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResLoginDTO res = new ResLoginDTO();
        SystemUserEntity currentUser = this.systemUserService.handleGetUserByUsername(loginDTO.getUserName());
        if (currentUser != null) {
            ResLoginDTO.UserLoginInfo userLoginInfo = new ResLoginDTO.UserLoginInfo(currentUser.getId(), currentUser.getUserName(), currentUser.getFullName());
            res.setUserLoginInfo(userLoginInfo);
        }

        res.setAccessToken(access_token);
        System.out.println("Login successful: " + loginDTO.getUserName());
        return ResponseEntity.ok(res);
    }
}
