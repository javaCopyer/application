package com.muteng.dgjs.common.utils;

import com.muteng.dgjs.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Author:JJH
 * Date : 2018-11-23 9:34
 * Description: <判断是否属于公司员工>
 * Version: V1.0
 */
@Component
public class Staffvalidator {
    @Autowired
    private  UserMapper userMapper;

    public static Staffvalidator staffvalidator;
    /**
     * 根据手机号查询员工表是否存在
     * @return ture or false
     */
    public static boolean judgeStaff(String phone){
        System.out.println("sta==="+staffvalidator);
        System.out.println("mppp==="+staffvalidator.userMapper.toString());
        String st = staffvalidator.userMapper.queryStaffByPhone(phone);
        if(st != null){
            return true;
        }
        return false;
    }

}
