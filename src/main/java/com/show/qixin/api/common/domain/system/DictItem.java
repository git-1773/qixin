package com.show.qixin.api.common.domain.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Excel("dictItem")
@Table(name = "tb_dict_item")
public class DictItem {

    @Id
    @ExcelField(value = "字典项ID", width = 50)
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ExcelField(value = "字典类型ID", width = 100)
    private Long dictId;

    @ExcelField(value = "字典类型编码", width = 100)
    private String dictCode;

    @ExcelField(value = "字典项名称", width = 100)
    private String name;

    @ExcelField(value = "字典项值", width = 100)
    private Integer value;

    @ExcelField(value = "字典排序值", width = 100)
    private Integer sort;

    @ExcelField(value = "字典状态 1=启用,2=未启用", width = 100)
    private Integer status;

}
