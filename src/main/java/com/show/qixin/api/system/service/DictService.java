package com.show.qixin.api.system.service;

import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.system.DictItem;
import com.show.qixin.api.system.vo.DictItemVo;
import com.show.qixin.api.system.vo.DictVo;

import java.util.List;

public interface DictService {

    ResponseBean add(DictVo dictVo);

    ResponseBean page(Integer pageNum, Integer pageSize, DictVo dictVo);

    ResponseBean listAll();

    ResponseBean edit(DictVo dictVo);

    ResponseBean del(DictVo dictVo);

    ResponseBean addItem(DictItemVo dictItemVo);

    ResponseBean editItem(DictItemVo dictItemVo);

    ResponseBean delItem(DictItemVo dictItemVo);

    List<DictItem> listItem(String value);

    //字典类型分页查询

    //字典类型查询全部

    //字典类型添加

    //字典类型修改

    //字典类型删除

}
