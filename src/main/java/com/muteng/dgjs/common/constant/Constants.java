package com.muteng.dgjs.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snow on 2015/7/12.
 * 常量接口
 */
public interface Constants {

    /**
     * 系统级常量
     */
    public interface System {
        public static final String SUCCESSS = "1" ;  //1表示成功
        public static final String ERROR = "0";   //0表示失败
        public static final String OK_CODE = "000000"; //业务正常code
    }

    /**
     * 业务常量 普通常量等
     */
    public interface Common {
        public static final String PAGE_SIZE = "pageSize";  //分页的大小

        public static final String CURRENT_PAGE = "currentPage"; //当前页数

        public static final String SORT = "sort";  //排序方式

        public static final String ORDER = "order"; //排序字段
        
    }
    
}
