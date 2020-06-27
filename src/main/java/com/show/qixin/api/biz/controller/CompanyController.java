package com.show.qixin.api.biz.controller;

import com.show.qixin.api.biz.service.CompanyService;
import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.common.bean.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "企业信息", notes = "企业信息申报提交")
    @PostMapping("/declare")
    public ResponseBean declare(@RequestBody @Validated CompanyDeclareVO companyDeclareVO) {
        try {
            return companyService.declare(companyDeclareVO);
        } catch (Exception e) {
            log.error("企业信息申报提交.error" , e);
            e.printStackTrace();
            return ResponseBean.error("企业信息申报提交.error" + e);
        }
    }

}
