package com.show.qixin.api.system.service.impl;

import com.show.qixin.api.system.mapper.DictItemMapper;
import com.show.qixin.api.system.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictItemServiceImpl implements DictItemService {

    @Autowired
    private DictItemMapper dictItemMapper;

}
