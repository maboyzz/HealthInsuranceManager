package com.nthuy.healthinsurancemanager.controller;

import com.nthuy.healthinsurancemanager.Exception.UserNameExist;
import com.nthuy.healthinsurancemanager.dto.request.CreateUserEntityReq;
import com.nthuy.healthinsurancemanager.service.UserEntityService;
import com.nthuy.healthinsurancemanager.until.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEntityController {

    private final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }



    @PostMapping("users")
    @ApiMessage("Tạo người dùng mới")
    public ResponseEntity<Long> createUserEntity(
            @Valid
            @RequestBody CreateUserEntityReq createUserEntityReq
    ) throws UserNameExist {
        boolean userNameExists = this.userEntityService.handleCheckUserNameExists(createUserEntityReq.getUserName());
        if (userNameExists){
            throw new UserNameExist("Username " + createUserEntityReq.getUserName() + " đã tồn tại");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userEntityService.handleCreateUserEntity(createUserEntityReq));

    }
}
