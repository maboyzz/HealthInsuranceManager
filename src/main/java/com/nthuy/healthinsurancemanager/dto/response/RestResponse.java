package com.nthuy.healthinsurancemanager.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
    private int status;
    private String error;
    private Object message;
    private T data;
}
