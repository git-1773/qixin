package com.show.qixin.api.common.domain.biz;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Excel("company")
@Table(name = "tb_company")
public class Company {

    @Id
    @ExcelField(value = "企业编号", width = 50)
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ExcelField(value = "企业名称", width = 100)
    private String name;

    @ExcelField(value = "企业logo", width = 200)
    private String logo;



    @ExcelField(//
        value = "企业审核状态",
        readConverterExp = "未审核=1,审核中、待审核=2, 审核通过=3, 审核未通过=4",
        writeConverterExp = "1=未审核,2=审核中、待审核, 3=审核通过, 4=审核未通过"
        ,width = 80
    )
    private Integer auditStatus;

    private Long createById;

    @ExcelField(value = "创建时间", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date createTime;

    private Long updateById;

    @ExcelField(value = "修改时间", dateFormat = "yyyy年MM月dd日 HH:mm:ss",width = 180)
    private Date updateTime;

    /**
     * 企业对应注册信息
     */
    private CompanyRegisterInfo companyRegisterInfo;

}
