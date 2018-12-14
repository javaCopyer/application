package com.muteng.dgjs.service.impl;

import com.muteng.dgjs.DTO.UserM;
import com.muteng.dgjs.dao.UtilMapper;
import com.muteng.dgjs.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author:JJH
 * Date : 2018-10-30 17:45
 * Description: <描述>
 */

@Service
public class UtilServiceImpl implements UtilService {
    @Autowired
    private UtilMapper utilMapper;

    @Override
    public List<Date> selectHoliday() {
        return utilMapper.selectHoliday();
    }

    @Override
    public List<Date> selectworkHoliday() {
        return utilMapper.selectworkHoliday();
    }
}
