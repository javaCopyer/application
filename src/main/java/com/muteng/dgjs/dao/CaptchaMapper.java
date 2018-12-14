package com.muteng.dgjs.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.dao.helper.DefaultMapper;
import com.muteng.dgjs.domain.Captcha;
public interface CaptchaMapper extends DefaultMapper<Captcha>{

	@Select("SELECT * FROM captcha WHERE mobile = #{phone} order by id desc limit 1")
	Captcha queryByPhone(Map<String,Object> map);
}
