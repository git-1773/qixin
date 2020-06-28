package com.show.qixin.api.biz.controller;

import com.alibaba.fastjson.JSON;
import com.show.qixin.api.biz.service.CompanyService;
import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.config.web.plugin.annotation.ApiJsonObject;
import com.show.qixin.api.common.config.web.plugin.annotation.ApiJsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/company")
@Validated
@Api(value = "企业信息模块", tags = "企业信息接口")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 企业信息申报提交
     *
     * @return
     */
    @PostMapping("/declare")
    @ApiOperation(value = "企业信息申报提交", notes = "企业信息申报提交")
    @ApiJsonObject({
            @ApiJsonProperty(name = "name", description = "企业名称", paramType = "String" ,required = true),
            @ApiJsonProperty(name = "registeredCapital", description = "注册资本", paramType = "Integer"),
            @ApiJsonProperty(name = "actualPaidCapital", description = "实缴资本", paramType = "Integer"),
            @ApiJsonProperty(name = "capitalUnit", description = "资本金单位", paramType = "Integer" ),
            @ApiJsonProperty(name = "setUpDate", description = "成立日期", paramType = "Date" ),
            @ApiJsonProperty(name = "operaStatus", description = "经营状态", paramType = "Integer" ),
            @ApiJsonProperty(name = "unifiedSocialCreditCode", description = "统一社会信用代码", paramType = "String" ),
            @ApiJsonProperty(name = "businessRegistrationNum", description = "工商注册号", paramType = "String" ),
            @ApiJsonProperty(name = "taxpayerIdentificationNum", description = "纳税人识别号", paramType = "String" ),
            @ApiJsonProperty(name = "organizationCode", description = "组织机构代码", paramType = "String" ),
            @ApiJsonProperty(name = "companyType", description = "公司类型", paramType = "Integer" ),
            @ApiJsonProperty(name = "companyIndustry", description = "公司行业", paramType = "Integer" ),
            @ApiJsonProperty(name = "approvalDate", description = "核准日期", paramType = "Date" ),
            @ApiJsonProperty(name = "registrationAuthority", description = "登记机关", paramType = "String" ),
            @ApiJsonProperty(name = "businessTerm", description = "营业期限", paramType = "Integer" ),
            @ApiJsonProperty(name = "taxpayerQualification", description = "纳税人资质", paramType = "Integer" ),
            @ApiJsonProperty(name = "staffSize", description = "人员规模", paramType = "Integer"),
            @ApiJsonProperty(name = "insuredNum", description = "参保人数", paramType = "Integer" ),
            @ApiJsonProperty(name = "historyName", description = "曾用名", paramType = "String" ),
            @ApiJsonProperty(name = "englishName", description = "英文名", paramType = "String" )
//            @ApiImplicitParam(name = "params", value = "企业申报信息", dataType = "com.show.qixin.api.biz.vo.CompanyDeclareVO" ,required = true),
    })
    public ResponseBean declare(@RequestBody @Validated Map<String, Object> params) {
        try {
            System.out.println(params);
            Object companyDeclare = params.get("companyDeclare");
            System.out.println("companyDeclare.toString=> " + companyDeclare.toString());
            CompanyDeclareVO companyDeclareVO = JSON.parseObject(JSON.toJSONString(companyDeclare), CompanyDeclareVO.class);
            return companyService.declare(companyDeclareVO);
        } catch (Exception e) {
            log.error("企业信息申报提交.error" , e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage() + e);
        }
    }

}
