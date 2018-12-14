package com.muteng.dgjs.DTO;

import com.muteng.dgjs.common.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Author:JJH
 * Date : 2018-10-30 17:02
 * Description: <取现页面返回实体类>
 */
@Data
public class UserM {
    private Long userid;
    private Double Account;   //余额
    private String name;
    private Integer isauth;  //1为已经实名 0为未实名
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String payday;    //预计审核日期

}
