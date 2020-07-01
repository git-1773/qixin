package com.show.qixin.api.biz.vo;

import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 企业信息申报vo
 */
@Data
public class CompanyDeclareVO {

    @ApiParam(value = "企业名称")
    @ExcelField(value = "企业名称", width = 100)
    @NotBlank(message = "企业名称不能为空")
    private String name;

    @ApiParam(value = "注册资本")
    //注册资本
    private Integer registeredCapital;

    //实缴资本
    private Integer actualPaidCapital = 0;

    //资本金单位
    private Integer capitalUnit = 0;

    //成立日期
    @ExcelField(value = "成立日期", dateFormat = "yyyy/MM/dd",width = 100)
    private Date setUpDate;

    //经营状态
    private Integer operaStatus = 0;

    //统一社会信用代码
    private String unifiedSocialCreditCode;

    //工商注册号
    private String businessRegistrationNum;

    //纳税人识别号
    private String taxpayerIdentificationNum;

    //组织机构代码
    private String organizationCode;

    //公司类型
    private Integer companyType = 0;

    //公司行业
    private Integer companyIndustry = 0;

    //核准日期
    private Date approvalDate;

    //登记机关
    private String registrationAuthority;

    //营业期限
    private Date businessTerm;

    //纳税人资质
    private Integer taxpayerQualification = 0;

    //人员规模
    private Integer staffSize = 0;

    //参保人数
    private Integer insuredNum = 0;

    //曾用名
    private String historyName;

    //英文名
    private String englishName;

}
