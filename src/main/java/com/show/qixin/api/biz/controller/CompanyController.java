package com.show.qixin.api.biz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.show.qixin.api.biz.service.CompanyService;
import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.common.bean.ResponseBean;
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
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "企业名称", dataType = "String" ,required = true),
//            @ApiImplicitParam(name = "registeredCapital", value = "注册资本", dataType = "Integer"),
//            @ApiImplicitParam(name = "actualPaidCapital", value = "实缴资本", dataType = "Integer"),
//            @ApiImplicitParam(name = "capitalUnit", value = "资本金单位", dataType = "Integer" ),
//            @ApiImplicitParam(name = "setUpDate", value = "成立日期", dataType = "Date" ),
//            @ApiImplicitParam(name = "operaStatus", value = "经营状态", dataType = "Integer" ),
//            @ApiImplicitParam(name = "unifiedSocialCreditCode", value = "统一社会信用代码", dataType = "String" ),
//            @ApiImplicitParam(name = "businessRegistrationNum", value = "工商注册号", dataType = "String" ),
//            @ApiImplicitParam(name = "taxpayerIdentificationNum", value = "纳税人识别号", dataType = "String" ),
//            @ApiImplicitParam(name = "organizationCode", value = "组织机构代码", dataType = "String" ),
//            @ApiImplicitParam(name = "companyType", value = "公司类型", dataType = "Integer" ),
//            @ApiImplicitParam(name = "companyIndustry", value = "公司行业", dataType = "Integer" ),
//            @ApiImplicitParam(name = "approvalDate", value = "核准日期", dataType = "Date" ),
//            @ApiImplicitParam(name = "registrationAuthority", value = "登记机关", dataType = "String" ),
//            @ApiImplicitParam(name = "businessTerm", value = "营业期限", dataType = "Integer" ),
//            @ApiImplicitParam(name = "taxpayerQualification", value = "纳税人资质", dataType = "Integer" ),
//            @ApiImplicitParam(name = "staffSize", value = "人员规模", dataType = "Integer"),
//            @ApiImplicitParam(name = "insuredNum", value = "参保人数", dataType = "Integer" ),
//            @ApiImplicitParam(name = "historyName", value = "曾用名", dataType = "String" ),
//            @ApiImplicitParam(name = "englishName", value = "英文名", dataType = "String" )
//    })
    public ResponseBean declare(@RequestBody @Validated Map<String, Object> map) {
        try {
            System.out.println(map);
            Object companyDeclare = map.get("companyDeclare");
            System.out.println("companyDeclare.toString=> " + companyDeclare.toString());
            CompanyDeclareVO companyDeclareVO = new ObjectMapper().readValue(companyDeclare.toString(), CompanyDeclareVO.class);
            return companyService.declare(companyDeclareVO);
        } catch (Exception e) {
            log.error("企业信息申报提交.error" , e);
            e.printStackTrace();
            return ResponseBean.error("企业信息申报提交.error" + e);
        }
    }

}
