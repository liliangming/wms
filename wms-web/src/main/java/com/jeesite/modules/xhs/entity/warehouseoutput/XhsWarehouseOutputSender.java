/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouseoutput;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 出库信息Entity
 * @author liliangming
 * @version 2018-12-20
 */
@Table(name="xhs_warehouse_output_sender", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="output_id", attrName="outputId.id", label="出库单号"),
		@Column(name="sender_name", attrName="senderName", label="寄件人姓名", queryType=QueryType.LIKE),
		@Column(name="sender_addr", attrName="senderAddr", label="寄件人地址"),
		@Column(name="sender_phone", attrName="senderPhone", label="寄件人电话"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class XhsWarehouseOutputSender extends DataEntity<XhsWarehouseOutputSender> {
	
	private static final long serialVersionUID = 1L;
	private XhsWarehouseOutput outputId;		// 出库单号 父类
	private String senderName;		// 寄件人姓名
	private String senderAddr;		// 寄件人地址
	private String senderPhone;		// 寄件人电话
	
	public XhsWarehouseOutputSender() {
		this(null);
	}


	public XhsWarehouseOutputSender(XhsWarehouseOutput outputId){
		this.outputId = outputId;
	}
	
	@Length(min=0, max=50, message="出库单号长度不能超过 50 个字符")
	public XhsWarehouseOutput getOutputId() {
		return outputId;
	}

	public void setOutputId(XhsWarehouseOutput outputId) {
		this.outputId = outputId;
	}
	
	@Length(min=0, max=100, message="寄件人姓名长度不能超过 100 个字符")
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	@Length(min=0, max=200, message="寄件人地址长度不能超过 200 个字符")
	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
	
	@Length(min=0, max=20, message="寄件人电话长度不能超过 20 个字符")
	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	
}