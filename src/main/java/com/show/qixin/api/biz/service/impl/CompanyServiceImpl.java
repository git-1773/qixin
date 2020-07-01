package com.show.qixin.api.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.show.qixin.api.biz.converter.CompanyConverter;
import com.show.qixin.api.biz.converter.CompanyRegisterInfoConverter;
import com.show.qixin.api.biz.mapper.CompanyDeclareHistoryMapper;
import com.show.qixin.api.biz.mapper.CompanyMapper;
import com.show.qixin.api.biz.mapper.CompanyRegisterInfoMapper;
import com.show.qixin.api.biz.service.CompanyService;
import com.show.qixin.api.biz.vo.CompanyDeclareVO;
import com.show.qixin.api.biz.vo.CompanyEditVO;
import com.show.qixin.api.biz.vo.CompanyVO;
import com.show.qixin.api.common.bean.LoginUser;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.biz.Company;
import com.show.qixin.api.common.domain.biz.CompanyDeclareHistory;
import com.show.qixin.api.common.domain.biz.CompanyRegisterInfo;
import com.show.qixin.api.common.domain.system.User;
import com.show.qixin.api.system.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyRegisterInfoMapper companyRegisterInfoMapper;
    @Autowired
    private CompanyDeclareHistoryMapper companyDeclareHistoryMapper;

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
    public ResponseBean declare(CompanyDeclareVO companyDeclareVO) throws Exception{
        User loginUser = ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUser();
        // 校验公司名称是否有效
        Company companyOne = new Company();
        companyOne.setName(companyDeclareVO.getName());
        companyOne = companyMapper.selectOne(companyOne);
        if(companyOne == null){
            //不存在则新建保存
            companyOne = CompanyConverter.converterToCompany(companyDeclareVO);
            companyOne.setAuditStatus(1);
            companyOne.setCreateById(loginUser.getId());
            companyOne.setCreateTime(new Date());
            companyOne.setUpdateById(companyOne.getCreateById());
            companyOne.setUpdateTime(companyOne.getCreateTime());
            int insert = companyMapper.insert(companyOne);
            if(insert < 1){
                return ResponseBean.error("企业信息保存异常");
            }
            CompanyRegisterInfo companyRegisterInfo = CompanyRegisterInfoConverter.converterToCompanyRegisterInfo(companyDeclareVO);
            companyRegisterInfo.setId(companyOne.getId());
            companyRegisterInfo.setUpdateByName(loginUser.getNickname());
            companyRegisterInfo.setUpdateTime(new Date());
            int insertCRI = companyRegisterInfoMapper.insert(companyRegisterInfo);
            if(insertCRI < 1){
                throw new Exception("企业申报信息保存异常");
            }
        }else {
            // 检查已存在企业申报人,是否和当前申报人一致,一致则更新,不一致则提示,该企业已被他人申报
            if(companyOne.getCreateById().equals(loginUser.getId())){
                CompanyRegisterInfo companyRegisterInfo = companyRegisterInfoMapper.selectByPrimaryKey(companyOne.getId());
                if(companyRegisterInfo == null){
                    companyRegisterInfo = CompanyRegisterInfoConverter.converterToCompanyRegisterInfo(companyDeclareVO);
                    companyRegisterInfo.setId(companyOne.getId());
                    companyRegisterInfo.setUpdateByName(loginUser.getNickname());
                    companyRegisterInfo.setUpdateTime(new Date());
                    int insert = companyRegisterInfoMapper.insert(companyRegisterInfo);
                    if(insert < 1){
                        throw new Exception("企业申报信息保存异常");
                    }
                }else {
                    Example example = new Example(CompanyRegisterInfo.class);
                    example.createCriteria().andEqualTo(companyRegisterInfo.getId());
                    companyRegisterInfo = CompanyRegisterInfoConverter.converterToCompanyRegisterInfo(companyDeclareVO);
                    int i = companyRegisterInfoMapper.updateByExampleSelective(companyRegisterInfo, example);
                    if(i < 1){
                        throw new Exception("企业申报信息更新异常");
                    }
                }
            }else {
                log.info("姓名[" + loginUser.getNickname() + "]重复申报企业[" + companyDeclareVO.getName() + "]的信息,userId=" + loginUser.getId());
                throw new Exception("企业[" + companyDeclareVO.getName() + "]已被他人申报！");
            }
        }
        // 校验公司名称是否存在(不存在时创建公司信息、存在时检查公司状态如果审核通过则需要重新设置为审核中)
        // 保存or更新 公司申报信息
        // 创建公司申报记录
        CompanyDeclareHistory companyDeclareHistory = new CompanyDeclareHistory();
        companyDeclareHistory.setCompanyId(companyOne.getId());
        companyDeclareHistory.setCompanyName(companyOne.getName());
        companyDeclareHistory.setCreateById(loginUser.getId());
        companyDeclareHistory.setCreateByName(loginUser.getNickname());
        companyDeclareHistory.setContentJsonStr(JSON.toJSONString(companyDeclareVO));
        int insert = companyDeclareHistoryMapper.insert(companyDeclareHistory);
        if(insert < 1){
            log.error("-----公司申报记录保存异常-----", JSON.toJSONString(companyDeclareVO));
        }
        return ResponseBean.success( );
    }
}
