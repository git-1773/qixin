package com.show.qixin.api.system.mapper;

import com.show.qixin.api.common.domain.system.LoginLog;
import com.show.qixin.api.system.vo.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface LoginLogMapper extends Mapper<LoginLog> {

    /**
     * 用户登入报表
     *
     * @param userVO
     * @return
     */
    List<Map<String, Object>> userLoginReport(UserVO userVO);

}
