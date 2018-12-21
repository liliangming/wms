/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.customer;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 客户信息Entity
 * @author liliangming
 * @version 2018-12-14
 */
@Table(name="xhs_customer_mail", alias="a", columns={
		@Column(name="id", attrName="id", label="主键id", isPK=true),
		@Column(name="customer_id", attrName="customerId.id", label="客户id"),
		@Column(name="mail_name", attrName="mailName", label="收件人姓名", queryType=QueryType.LIKE),
		@Column(name="mail_addr", attrName="mailAddr", label="收件人地址"),
		@Column(name="mail_phone", attrName="mailPhone", label="收件人电话"),
		@Column(name="is_default", attrName="isDefault", label="默认地址"),
		@Column(name="sort", attrName="sort", label="权重"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.create_date ASC"
)
public class XhsCustomerMail extends DataEntity<XhsCustomerMail> {
	
	private static final long serialVersionUID = 1L;
	private XhsCustomerInfo customerId;		// 客户id 父类
	private String mailName;		// 收件人姓名
	private String mailAddr;		// 收件人地址
	private String mailPhone;		// 收件人电话
	private Long isDefault;		// 默认地址
	private Long sort;		// 权重
	
	public XhsCustomerMail() {
		this(null);
	}


	public XhsCustomerMail(XhsCustomerInfo customerId){
		this.customerId = customerId;
	}
	
	@Length(min=0, max=50, message="客户id长度不能超过 50 个字符")
	public XhsCustomerInfo getCustomerId() {
		return customerId;
	}

	public void setCustomerId(XhsCustomerInfo customerId) {
		this.customerId = customerId;
	}
	
	@NotBlank(message="收件人姓名不能为空")
	@Length(min=0, max=100, message="收件人姓名长度不能超过 100 个字符")
	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}
	
	@NotBlank(message="收件人地址不能为空")
	@Length(min=0, max=100, message="收件人地址长度不能超过 100 个字符")
	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	
	@NotBlank(message="收件人电话不能为空")
	@Length(min=0, max=20, message="收件人电话长度不能超过 20 个字符")
	public String getMailPhone() {
		return mailPhone;
	}

	public void setMailPhone(String mailPhone) {
		this.mailPhone = mailPhone;
	}
	
	public Long getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Long isDefault) {
		this.isDefault = isDefault;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}