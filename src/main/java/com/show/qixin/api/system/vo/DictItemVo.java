package com.show.qixin.api.system.vo;

import lombok.Data;

@Data
public class DictItemVo {

    private Long id;

    private Long dictId;

    private String name;

    private Integer value;

    private Integer sort;

    private Integer status;
}
