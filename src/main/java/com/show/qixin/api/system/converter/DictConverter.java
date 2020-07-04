package com.show.qixin.api.system.converter;

import com.show.qixin.api.common.domain.system.Dict;
import com.show.qixin.api.system.vo.DictVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class DictConverter {

    /**
     * 转voList
     * @param dicts
     * @return
     */
    public List<DictVo> converterToDictVOList(List<Dict> dicts){
        List<DictVo> dictVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(dicts)){
            for (Dict dict : dicts) {
                DictVo dictVo = converterToDictVO(dict);
                dictVOS.add(dictVo);
            }
        }
        return dictVOS;
    }

    /**
     * 转vo
     * @return
     */
    public  DictVo converterToDictVO(Dict dict){
        DictVo dictVo = new DictVo();
        BeanUtils.copyProperties(dict,dictVo);
        return dictVo;
    }

}
