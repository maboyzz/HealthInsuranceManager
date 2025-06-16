package com.nthuy.healthinsurancemanager.dto.request;

import com.nthuy.healthinsurancemanager.dto.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPaginationDTO {
    private Meta meta;
    private Object results;
}
