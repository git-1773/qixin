package com.show.qixin.api.common.domain.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Excel("dict")
@Table(name = "tb_dict")
public class Dict {

    @Id
    @ExcelField(value = "字典类型ID", width = 50)
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ExcelField(value = "字典类型名称", width = 100)
    private String dictName;

    @ExcelField(value = "字典类型编码", width = 100)
    private String dictCode;

}
