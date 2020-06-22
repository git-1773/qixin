package com.show.qixin.api.common.domain.biz;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 企业注册信息
 */
@Data
@Excel("company_register_info")
@Table(name = "tb_company_register_info")
public class CompanyRegisterInfo {

    @Id
    @ExcelField(value = "企业编号", width = 50)
    private Long id;

    //注册资本
    private Integer registeredCapital;

    //实缴资本
    private Integer actualPaidCapital;

    //资本金单位
    private Integer capitalUnit;

    //成立日期
    @ExcelField(value = "出生日期", dateFormat = "yyyy/MM/dd",width = 100)
    private Date setUpDate;

    //经营状态
    private Integer operaStatus;

    //统一社会信用代码
    private String unifiedSocialCreditCode;

    //工商注册号
    private String businessRegistrationNum;

    //纳税人识别号
    private String taxpayerIdentificationNum;

    //组织机构代码
    private String organizationCode;

    //公司类型
    private Integer companyType;

    //公司行业
    private Integer companyIndustry;

    //核准日期
    private Date approvalDate;

    //登记机关
    private String registrationAuthority;

    //营业期限
    private Date businessTerm;

    //纳税人资质
    private Integer taxpayerQualification;

    //人员规模
    private Integer staffSize;

    //参保人数
    private Integer insuredNum;

    //曾用名
    private String historyName;

    //英文名
    private String englishName;

    //最新更新人
    private String updateBy;

    //最新更新时间
    private Date updateTime;

}
