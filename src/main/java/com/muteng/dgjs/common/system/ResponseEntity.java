package com.muteng.dgjs.common.system;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created by snow on 2015/7/12. 所有方法的返回类型
 */
@ToString(callSuper = true)
public class ResponseEntity implements Serializable {

    private static final long serialVersionUID = 7729482447094472913L;
    private Integer code; //业务编码
    private String msg; // 业务消息
    private Object obj; // 业务数据
   // private String status; // 接口状态

    public ResponseEntity() {

    }

    public ResponseEntity(Object data) {
        this.obj = data;
    }

    public ResponseEntity(Object data, Integer code) {
        this.obj = data;
        this.code = code;
    }

    public ResponseEntity(Object data, Integer code, String msg) {
        this.obj = data;
        this.code = code;
        this.msg = msg;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
    
}
