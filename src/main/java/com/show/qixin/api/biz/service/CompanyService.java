package com.show.qixin.api.biz.service;

import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.biz.vo.CompanyEditVO;
import com.show.qixin.api.biz.vo.CompanyVO;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.biz.Company;
import com.show.qixin.api.system.vo.PageVO;

public interface CompanyService {

    /**
     * 根据企业名称查询企业
     *
     * @param name 用户名
     * @return
     */
    Company findCompanyByName(String name);

    /**
     * 添加企业
     * @param companyVO
     */
    void add(CompanyVO companyVO);

    /**
     * 更新企业
     *
     * @param id
     * @param companyVO
     */
    void update(Long id, CompanyEditVO companyVO);

    /**
     * 企业列表
     * @param companyVO
     * @return
     */
    PageVO<CompanyVO> findCompanyList(Integer pageNum, Integer pageSize, CompanyVO companyVO);

    /**
     * 企业申报信息提交
     * @param companyDeclareVO
     * @return
     */
    ResponseBean declare(CompanyDeclareVO companyDeclareVO);
}
