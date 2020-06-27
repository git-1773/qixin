package com.show.qixin.api.biz.service.impl;

import com.show.qixin.api.biz.mapper.CompanyMapper;
import com.show.qixin.api.biz.service.CompanyService;
import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.biz.vo.CompanyEditVO;
import com.show.qixin.api.biz.vo.CompanyVO;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.biz.Company;
import com.show.qixin.api.system.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company findCompanyByName(String name) {
        Company company = new Company();
        company.setName(name);
        return companyMapper.selectOne(company);
    }

    @Override
    public void add(CompanyVO companyVO) {

    }

    @Override
    public void update(Long id, CompanyEditVO companyVO) {

    }

    @Override
    public PageVO<CompanyVO> findCompanyList(Integer pageNum, Integer pageSize, CompanyVO companyVO) {
        return null;
    }

    @Override
    @Transactional
    public ResponseBean declare(CompanyDeclareVO companyDeclareVO) {
        // 校验公司名称是否有效
        // 校验公司名称是否存在(不存在时创建公司信息、存在时检查公司状态如果审核通过则需要重新设置为审核中)
        // 保存or更新 公司申报信息
        // 创建公司申报记录

        return ResponseBean.success( );
    }
}
