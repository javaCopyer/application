package com.muteng.dgjs.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Device;
import com.muteng.dgjs.domain.User;
import com.dao.helper.DefaultMapper;

public interface DeviceMapper extends DefaultMapper<Device>{
	
	@Select("SELECT * FROM device WHERE uuid = #{uuid} order by id desc limit 1")
	Device queryByuuid(Map<String,Object> map);
}
