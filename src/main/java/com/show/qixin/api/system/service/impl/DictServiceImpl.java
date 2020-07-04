package com.show.qixin.api.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.system.Dict;
import com.show.qixin.api.common.domain.system.DictItem;
import com.show.qixin.api.system.converter.DictConverter;
import com.show.qixin.api.system.mapper.DictItemMapper;
import com.show.qixin.api.system.mapper.DictMapper;
import com.show.qixin.api.system.service.DictService;
import com.show.qixin.api.system.vo.DictItemVo;
import com.show.qixin.api.system.vo.DictVo;
import com.show.qixin.api.system.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictConverter dictConverter;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private DictItemMapper dictItemMapper;

    @Override
    public ResponseBean add(DictVo dictVo) {
        @NotBlank(message = "字典类型名称不能为空") String dictName = dictVo.getDictName();
        @NotBlank(message = "字典类型编码不能为空") String dictCode = dictVo.getDictCode();
        Example o = new Example(Dict.class);
        o.createCriteria().andEqualTo("dictCode", dictCode);
        int i = dictMapper.selectCountByExample(o);
        if(i !=0 ){
            String errMsg = "字典类型编码[" + dictCode + "]已被占用!";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictVo, dict);
        int insert = dictMapper.insert(dict);
        if(insert != 1){
            String errMsg = "字典类型添加异常!,编码为[" + dictCode + "]";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        return ResponseBean.success();
    }

    @Override
    public ResponseBean page(Integer pageNum, Integer pageSize, DictVo dictVo) {
        PageHelper.startPage(pageNum,pageSize);
        Example o = new Example(Dict.class);
        Example.Criteria criteria = o.createCriteria();
        if(dictVo != null){
            if(dictVo.getDictName() != null && !"".equals(dictVo.getDictName())){
                criteria.andLike("dicName","%" + dictVo.getDictName() + "%");
            }
            if(dictVo.getDictCode() != null && !"".equals(dictVo.getDictCode())){
                criteria.andLike("dictCode","%" + dictVo.getDictCode() + "%");
            }
        }
        List<Dict> dictList = dictMapper.selectByExample(o);
        List<DictVo> dictVoList = dictConverter.converterToDictVOList(dictList);
        PageInfo<Dict> info = new PageInfo<>(dictList);
        return ResponseBean.success(new PageVO<DictVo>(info.getTotal(), dictVoList));
    }

    @Override
    public ResponseBean listAll() {
        return ResponseBean.success(dictMapper.selectAll());
    }

    @Override
    public ResponseBean edit(DictVo dictVo) {
        if(dictVo == null || dictVo.getId() == null){
            return ResponseBean.error("参数无效");
        }
        Dict idDict = dictMapper.selectByPrimaryKey(dictVo.getId());
        if(idDict == null){
            String errMsg = "待编辑字典类型不存在";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        Example o = new Example(Dict.class);
        o.createCriteria().andEqualTo("dictCode",  dictVo.getDictCode());
        Dict codeDict = dictMapper.selectOneByExample(o);
        if(codeDict != null && codeDict.getId() != idDict.getId()){
            String errMsg = "字典类型编码[" + dictVo.getDictCode() + "]已被占用!";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictVo, dict);
//        int i = dictMapper.updateByPrimaryKeySelective(dict);
//        if(i != 1){
//            String errMsg = "字典类型 update 异常";
//            log.error(errMsg);
//            return ResponseBean.error(errMsg);
//        }
        return ResponseBean.success(dictMapper.updateByPrimaryKeySelective(dict));
    }

    @Override
    public ResponseBean del(DictVo dictVo) {
        if(dictVo == null || dictVo.getId() == null){
            return ResponseBean.error("参数无效");
        }
        Example o = new Example(DictItem.class);
        o.createCriteria().andEqualTo("dictId", dictVo.getId());
        int dictItems = dictItemMapper.selectCountByExample(o);
        if(dictItems > 0){
            String errMsg = "当前字典类型存在字典项,不允许删除";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        return ResponseBean.success(dictMapper.deleteByPrimaryKey(dictVo.getId()));
    }

    @Override
    public ResponseBean addItem(DictItemVo dictItemVo) {
        if(dictItemVo == null){
            return ResponseBean.error("参数无效");
        }
        @NotBlank(message = "字典类型ID无效") Long dictId = dictItemVo.getDictId();
        @NotBlank(message = "字典项名称无效") String dictItemName = dictItemVo.getName();
        @NotBlank(message = "字典项值无效") Integer dictItemValue = dictItemVo.getValue();
        Dict idDict = dictMapper.selectByPrimaryKey(dictItemVo.getDictId());
        if(idDict == null){
            String errMsg = "对应字典类型不存在";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        DictItem dictItem = new DictItem();
        BeanUtils.copyProperties(dictItemVo, dictItem);
        dictItem.setDictCode(idDict.getDictCode());
        dictItem.setSort(dictItemVo.getSort() == null ? 0 : dictItemVo.getSort());
        dictItem.setStatus(dictItemVo.getStatus() == null ? 1 : dictItemVo.getStatus());
        int insert = dictItemMapper.insert(dictItem);
        if(insert != 1){
            String errMsg = "字典项添加异常!";
            log.error(errMsg + dictItemVo.toString());
            return ResponseBean.error(errMsg);
        }
        return ResponseBean.success();
    }

    @Override
    public ResponseBean editItem(DictItemVo dictItemVo) {
        if(dictItemVo == null || dictItemVo.getId() == null){
            return ResponseBean.error("参数无效");
        }
        DictItem idDictItem = dictItemMapper.selectByPrimaryKey(dictItemVo.getId());
        if(idDictItem == null){
            String errMsg = "待编辑字典项不存在";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        Example o = new Example(DictItem.class);
        o.createCriteria().andEqualTo("dictId",  idDictItem.getDictId())
            .andEqualTo("value", dictItemVo.getValue());
        DictItem valueDictItem = dictItemMapper.selectOneByExample(o);
        if(valueDictItem != null && valueDictItem.getId() != valueDictItem.getId()){
            String errMsg = "字典项对应值[" + dictItemVo.getValue() + "]已被占用!";
            log.error(errMsg);
            return ResponseBean.error(errMsg);
        }
        DictItem dictItem = new DictItem();
        BeanUtils.copyProperties(dictItemVo, dictItem);
        return ResponseBean.success(dictItemMapper.updateByPrimaryKeySelective(dictItem));
    }

    @Override
    public ResponseBean delItem(DictItemVo dictItemVo) {
        if(dictItemVo == null || dictItemVo.getId() == null){
            return ResponseBean.error("参数无效");
        }
        return ResponseBean.success(dictMapper.deleteByPrimaryKey(dictItemVo.getId()));
    }

    @Override
    public List<DictItem> listItem(String value) {
        if(value == null || "".equals(value)){
            return Lists.newArrayList();
        }
        Example o = new Example(DictItem.class);
        Example.Criteria criteria = o.createCriteria();
        if(value.matches("^[0-9]*$")){
            criteria.andEqualTo("dictId", value);
        }else {
            criteria.andEqualTo("dictCode", value);
        }
        List<DictItem> dictItems = dictItemMapper.selectByExample(o);
        return dictItems;
    }
}
