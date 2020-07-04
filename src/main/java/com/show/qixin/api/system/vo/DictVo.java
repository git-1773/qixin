package com.show.qixin.api.system.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictVo {

    private Long id;

    @NotBlank(message = "名称不能为空")
    private String dictName;

    @NotBlank(message = "编码不能为空")
    private String dictCode;

}
