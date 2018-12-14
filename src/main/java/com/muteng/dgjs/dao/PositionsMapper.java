package com.muteng.dgjs.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Positions;

import java.util.List;
import java.util.Map;

public interface PositionsMapper extends DefaultMapper<Positions>{

    @Select("Select id,jobtitle from   positions where  parentid !=0 and producttype='招工'")
    List<Map<String,Object>> queryPositions();

}
