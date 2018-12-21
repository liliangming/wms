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
@Table(name="xhs_shipment_logistics", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="shipment_id", attrName="shipmentId.id", label="出货单号"),
		@Column(name="mail_type", attrName="mailType", label="快递类型"),
		@Column(name="mail_number", attrName="mailNumber", label="快递单号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class XhsShipmentLogistics extends DataEntity<XhsShipmentLogistics> {
	
	private static final long serialVersionUID = 1L;
	private XhsShipmentInfo shipmentId;		// 出货单号 父类
	private String mailType;		// 快递类型
	private String mailNumber;		// 快递单号
	
	public XhsShipmentLogistics() {
		this(null);
	}


	public XhsShipmentLogistics(XhsShipmentInfo shipmentId){
		this.shipmentId = shipmentId;
	}
	
	@Length(min=0, max=50, message="出货单号长度不能超过 50 个字符")
	public XhsShipmentInfo getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(XhsShipmentInfo shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	@Length(min=0, max=20, message="快递类型长度不能超过 20 个字符")
	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	
	@Length(min=0, max=50, message="快递单号长度不能超过 50 个字符")
	public String getMailNumber() {
		return mailNumber;
	}

	public void setMailNumber(String mailNumber) {
		this.mailNumber = mailNumber;
	}
	
}