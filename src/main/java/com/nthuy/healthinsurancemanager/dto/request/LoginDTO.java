package com.nthuy.healthinsurancemanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "username không đươợc để trống")
    private String userName;
    @NotBlank(message = "password không đươợc để trống")
    private String passWord;
}
