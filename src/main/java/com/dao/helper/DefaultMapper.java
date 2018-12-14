package com.dao.helper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface DefaultMapper<T> extends Mapper<T>,MySqlMapper<T>{

}
