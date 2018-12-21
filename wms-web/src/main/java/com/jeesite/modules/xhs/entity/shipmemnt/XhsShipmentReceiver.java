/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.shipmemnt;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 出货信息Entity
 * @author liliangming
 * @version 2018-12-18
 */
@Table(name="xhs_shipment_receiver", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="shipment_id", attrName="shipmentId.id", label="出货单号"),
		@Column(name="receiver_name", attrName="receiverName", label="收件人姓名", queryType=QueryType.LIKE),
		@Column(name="receiver_addr", attrName="receiverAddr", label="收件人地址"),
		@Column(name="receiver_phone", attrName="receiverPhone", label="收件人电话"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class XhsShipmentReceiver extends DataEntity<XhsShipmentReceiver> {
	
	private static final long serialVersionUID = 1L;
	private XhsShipmentInfo shipmentId;		// 出货单号 父类
	private String receiverName;		// 收件人姓名
	private String receiverAddr;		// 收件人地址
	private String receiverPhone;		// 收件人电话
	
	public XhsShipmentReceiver() {
		this(null);
	}


	public XhsShipmentReceiver(XhsShipmentInfo shipmentId){
		this.shipmentId = shipmentId;
	}
	
	@Length(min=0, max=50, message="出货单号长度不能超过 50 个字符")
	public XhsShipmentInfo getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(XhsShipmentInfo shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	@Length(min=0, max=100, message="收件人姓名长度不能超过 100 个字符")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Length(min=0, max=200, message="收件人地址长度不能超过 200 个字符")
	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	
	@Length(min=0, max=20, message="收件人电话长度不能超过 20 个字符")
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
}