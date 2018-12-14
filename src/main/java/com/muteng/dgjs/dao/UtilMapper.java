package com.muteng.dgjs.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muteng.dgjs.DTO.UserM;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Author:JJH
 * Date : 2018-10-30 17:49
 * Description: <描述>
 */
public interface UtilMapper {
        @Select("select time from holiday where contains = '不包含' ")
        List<Date> selectHoliday();

        @Select("select time from holiday where contains = '包含' ")
        List<Date> selectworkHoliday();
}
