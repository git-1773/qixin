package com.show.qixin.api.common.domain.biz;

import lombok.Data;

@Data
public class CompanyDeclareHistory {

    /**
     * 申报历史记录ID
     */
    private Long id;

    /**
     * 申报企业ID
     */
    private Long companyId;

    /**
     * 申报企业名称
     */
    private String companyName;

    /**
     * 申报人ID
     */
    private Long createById;

    /**
     * 申报人姓名
     */
    private String createByName;

    /**
     * 当前申报信息json串
     */
    private String contentJsonStr;

}
