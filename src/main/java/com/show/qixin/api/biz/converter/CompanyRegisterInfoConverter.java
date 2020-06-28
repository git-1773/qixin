package com.show.qixin.api.biz.converter;

import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.common.domain.biz.Company;
import com.show.qixin.api.common.domain.biz.CompanyRegisterInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyRegisterInfoConverter {

    /**
     * 转 CompanyRegisterInfo
     * @param companyDeclareVO
     * @return
     */
    public static CompanyRegisterInfo converterToCompanyRegisterInfo(CompanyDeclareVO companyDeclareVO) {

        CompanyRegisterInfo companyRegisterInfo = new CompanyRegisterInfo();
        BeanUtils.copyProperties(companyDeclareVO,companyRegisterInfo);

        return companyRegisterInfo;

    }

}
