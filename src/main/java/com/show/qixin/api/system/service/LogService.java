package com.show.qixin.api.system.service;

import com.show.qixin.api.common.domain.system.Log;
import com.show.qixin.api.system.vo.LogVO;
import com.show.qixin.api.system.vo.PageVO;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface LogService  {

    /**
     * 异步保存操作日志
     */
    @Async("CodeAsyncThreadPool")
    void saveLog(Log log);


    /**
     * 删除登入日志
     * @param id
     */
    void delete(Long id);


    /**
     * 登入日志列表
     * @param pageNum
     * @param pageSize
     * @param logVO
     * @return
     */
    PageVO<LogVO> findLogList(Integer pageNum, Integer pageSize, LogVO logVO);

    /**
     * 批量删除登入日志
     * @param list
     */
    void batchDelete(List<Long> list);
}
