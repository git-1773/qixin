package com.show.qixin.api.system.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.show.qixin.api.common.annotation.ControllerEndpoint;
import com.show.qixin.api.common.bean.ResponseBean;
import com.show.qixin.api.common.domain.system.DictItem;
import com.show.qixin.api.system.service.DictService;
import com.show.qixin.api.system.vo.DictItemVo;
import com.show.qixin.api.system.vo.DictVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dict")
@Validated
@Api(value = "系统字典模块", tags = "系统字典接口")
@Slf4j
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 添加字典类型
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "添加字典类型失败", operation = "添加字典类型")
    @ApiOperation(value = "添加字典类型", notes = "添加字典类型信息")
//    @RequiresPermissions({"dict:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated Map<String, Object> params) {
        try {
            DictVo dictVo = JSON.parseObject(JSON.toJSONString(params), DictVo.class);
            return dictService.add(dictVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "分页查询字典类型", notes = "分页查询字典类型")
    @PostMapping("/page")
    public ResponseBean page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestBody @Validated Map<String, Object> params){
        try {
            DictVo dictVo = JSON.parseObject(JSON.toJSONString(params), DictVo.class);
            return dictService.page(pageNum, pageSize, dictVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询全部字典类型列表", notes = "查询全部字典类型列表")
    @PostMapping("/listAll")
    public ResponseBean listAll(){
        try {
            return dictService.listAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ControllerEndpoint(exceptionMessage = "编辑字典类型信息失败", operation = "编辑字典类型信息")
    @ApiOperation(value = "编辑字典类型信息", notes = "编辑字典类型信息")
    @PostMapping("/edit")
    public ResponseBean edit(@RequestBody @Validated Map<String, Object> params){
        try {
            DictVo dictVo = JSON.parseObject(JSON.toJSONString(params), DictVo.class);
            return dictService.edit(dictVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ControllerEndpoint(exceptionMessage = "删除字典类型信息失败", operation = "删除字典类型信息")
    @ApiOperation(value = "删除字典类型信息", notes = "删除字典类型信息")
    @PostMapping("/del")
    public ResponseBean del(@RequestBody @Validated Map<String, Object> params){
        try {
            DictVo dictVo = JSON.parseObject(JSON.toJSONString(params), DictVo.class);
            return dictService.del(dictVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    /**
     * 添加字典类型
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "添加字典项失败", operation = "添加字典项")
    @ApiOperation(value = "添加字典项信息", notes = "添加字典项信息")
    @PostMapping("/addItem")
    public ResponseBean addItem(@RequestBody @Validated Map<String, Object> params) {
        try {
            DictItemVo dictItemVo = JSON.parseObject(JSON.toJSONString(params), DictItemVo.class);
            return dictService.addItem(dictItemVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ControllerEndpoint(exceptionMessage = "编辑字典项信息失败", operation = "编辑字典项信息")
    @ApiOperation(value = "编辑字典项信息", notes = "编辑字典项信息")
    @PostMapping("/editItem")
    public ResponseBean editItem(@RequestBody @Validated Map<String, Object> params){
        try {
            DictItemVo dictItemVo = JSON.parseObject(JSON.toJSONString(params), DictItemVo.class);
            return dictService.editItem(dictItemVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @ControllerEndpoint(exceptionMessage = "删除字典项信息失败", operation = "删除字典项信息")
    @ApiOperation(value = "删除字典项信息", notes = "删除字典项信息")
    @PostMapping("/delItem")
    public ResponseBean delItem(@RequestBody @Validated Map<String, Object> params){
        try {
            DictItemVo dictItemVo = JSON.parseObject(JSON.toJSONString(params), DictItemVo.class);
            return dictService.delItem(dictItemVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/listItem/{value}")
    public List<DictItem> listByDict(@PathVariable String value){
        try {
            return dictService.listItem(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

}
