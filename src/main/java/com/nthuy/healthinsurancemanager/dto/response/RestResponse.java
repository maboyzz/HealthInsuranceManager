package com.nthuy.healthinsurancemanager.dto.response;

import com.nthuy.healthinsurancemanager.constant.EnumErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
    private int statusCode;
    private EnumErrorCode errorCode;
    private Object message;
    private T data;
}
