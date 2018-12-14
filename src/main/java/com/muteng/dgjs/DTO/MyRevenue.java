package com.muteng.dgjs.DTO;

import lombok.Data;

/**
 * Author:JJH
 * Date : 2018-10-27 11:06
 * Description: <收入页面实体类>
 */
@Data
public class MyRevenue {
    private Long userid;
    private Integer account;//余额
    private Integer wqrRevenue;//未确认收入
    private Integer lzRevenue;//离职作废收入
    private Integer dzRevenue;//到账收入
}
