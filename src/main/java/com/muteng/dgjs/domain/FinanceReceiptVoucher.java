package com.muteng.dgjs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "finance_receipt_voucher")
public class FinanceReceiptVoucher {
	private Long id;
	private String number;
	private Integer num;
	private String type;
	private String vouchertype;
	private String voucherdata;
	private String remark;
	private Timestamp createtime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name = "num")
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "vouchertype")
	public String getVouchertype() {
		return vouchertype;
	}
	public void setVouchertype(String vouchertype) {
		this.vouchertype = vouchertype;
	}
	
	@Column(name = "voucherdata")
	public String getVoucherdata() {
		return voucherdata;
	}
	public void setVoucherdata(String voucherdata) {
		this.voucherdata = voucherdata;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "createtime")
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	
}
