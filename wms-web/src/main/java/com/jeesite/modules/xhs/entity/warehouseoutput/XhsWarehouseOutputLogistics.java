/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouseoutput;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 出库信息Entity
 * @author liliangming
 * @version 2018-12-20
 */
@Table(name="xhs_warehouse_output_logistics", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="output_id", attrName="outputId.id", label="出货单号"),
		@Column(name="mail_type", attrName="mailType", label="快递类型"),
		@Column(name="mail_number", attrName="mailNumber", label="快递单号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class XhsWarehouseOutputLogistics extends DataEntity<XhsWarehouseOutputLogistics> {
	
	private static final long serialVersionUID = 1L;
	private XhsWarehouseOutput outputId;		// 出货单号 父类
	private String mailType;		// 快递类型
	private String mailNumber;		// 快递单号
	
	public XhsWarehouseOutputLogistics() {
		this(null);
	}


	public XhsWarehouseOutputLogistics(XhsWarehouseOutput outputId){
		this.outputId = outputId;
	}
	
	@Length(min=0, max=50, message="出货单号长度不能超过 50 个字符")
	public XhsWarehouseOutput getOutputId() {
		return outputId;
	}

	public void setOutputId(XhsWarehouseOutput outputId) {
		this.outputId = outputId;
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