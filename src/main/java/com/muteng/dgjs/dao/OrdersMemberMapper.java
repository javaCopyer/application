package com.muteng.dgjs.dao;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.OrdersMember;
import org.apache.ibatis.annotations.Select;

public interface OrdersMemberMapper extends DefaultMapper<OrdersMember>{

    @Select("select * from ordersmember where userid =#{id} order by createtime desc limit 1")
    OrdersMember queryorderById(Long id);

    @Select("select orderid from orderlist where orderNo = #{orderNo}")
    String queryorderByNo(String orderNo);
}
