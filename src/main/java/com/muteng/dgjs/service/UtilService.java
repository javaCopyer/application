package com.muteng.dgjs.service;

import com.muteng.dgjs.DTO.UserM;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author:JJH
 * Date : 2018-10-30 17:27
 * Description: <描述>
 */
public interface UtilService {
    List<Date> selectHoliday();

    List<Date> selectworkHoliday();
}
