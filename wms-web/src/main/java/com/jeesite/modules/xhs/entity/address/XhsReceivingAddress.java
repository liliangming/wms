/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.address;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 收货地址信息Entity
 * @author liliangming
 * @version 2018-12-18
 */
@Table(name="xhs_receiving_address", alias="a", columns={
		@Column(name="id", attrName="id", label="主键id", isPK=true),
		@Column(name="receiver_name", attrName="receiverName", label="收件人", queryType=QueryType.LIKE),
		@Column(name="receiver_phone", attrName="receiverPhone", label="电话号码"),
		@Column(name="receiver_addr", attrName="receiverAddr", label="地址"),
		@Column(name="is_default", attrName="isDefault", label="默认地址"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class XhsReceivingAddress extends DataEntity<XhsReceivingAddress> {
	
	private static final long serialVersionUID = 1L;
	private String receiverName;		// 收件人
	private String receiverPhone;		// 电话号码
	private String receiverAddr;		// 地址
	private Long isDefault;		// 默认地址
	
	public XhsReceivingAddress() {
		this(null);
	}

	public XhsReceivingAddress(String id){
		super(id);
	}
	
	@NotBlank(message="收件人不能为空")
	@Length(min=0, max=100, message="收件人长度不能超过 100 个字符")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@NotBlank(message="电话号码不能为空")
	@Length(min=0, max=20, message="电话号码长度不能超过 20 个字符")
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	@NotBlank(message="地址不能为空")
	@Length(min=0, max=200, message="地址长度不能超过 200 个字符")
	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	
	public Long getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Long isDefault) {
		this.isDefault = isDefault;
	}
	
}