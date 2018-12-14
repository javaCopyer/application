package com.muteng.dgjs.vo;


import java.util.List;

import com.muteng.dgjs.DTO.OrderuserPaymentvoucherDTO;
import com.muteng.dgjs.domain.Orderlist;
import com.muteng.dgjs.domain.User;

import lombok.Data;
/**
 * 入职详情VO
* @author 
* @date 2018年10月25日 下午2:40:00
* @version
 */
@Data
public class EntryDetailsResponse {
   
    private Orderlist orderList;
    private User user;
    private List<OrderuserPaymentvoucherDTO> orderuser;
    
}
