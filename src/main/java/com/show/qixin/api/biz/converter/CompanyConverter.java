package com.show.qixin.api.biz.converter;

import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.biz.vo.CompanyVO;
import com.show.qixin.api.common.domain.biz.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

    /**
     * 转vo
     * @return
     */
    public static Company converterToCompany(CompanyVO companyVO){
        Company company = new Company();
        BeanUtils.copyProperties(companyVO,company);

        return company;
    }

    /**
     * 转vo
     * @return
     */
    public static CompanyVO converterToCompanyVO(Company company){
        CompanyVO companyVO = new CompanyVO();
        BeanUtils.copyProperties(company,companyVO);

        return companyVO;
    }

    public static Company converterToCompany(CompanyDeclareVO companyDeclareVO) {

        Company company = new Company();
        BeanUtils.copyProperties(companyDeclareVO,company);

        return company;

    }
}
