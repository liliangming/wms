/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.customer;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 客户信息Entity
 * @author liliangming
 * @version 2018-12-14
 */
@Table(name="xhs_customer_contact", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="customer_id", attrName="customerId.id", label="客户id"),
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="duty", attrName="duty", label="职务"),
		@Column(name="phone_number", attrName="phoneNumber", label="办公电话"),
		@Column(name="mobile_number", attrName="mobileNumber", label="移动电话"),
		@Column(name="email", attrName="email", label="邮箱"),
		@Column(name="qq_number", attrName="qqNumber", label="QQ"),
		@Column(name="webchat_number", attrName="webchatNumber", label="微信"),
		@Column(name="addr", attrName="addr", label="地址"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="sort", attrName="sort", label="权重"),
	}, orderBy="a.create_date ASC"
)
public class XhsCustomerContact extends DataEntity<XhsCustomerContact> {
	
	private static final long serialVersionUID = 1L;
	private XhsCustomerInfo customerId;		// 客户id 父类
	private String name;		// 姓名
	private Long sex;		// 性别
	private String duty;		// 职务
	private String phoneNumber;		// 办公电话
	private String mobileNumber;		// 移动电话
	private String email;		// 邮箱
	private String qqNumber;		// QQ
	private String webchatNumber;		// 微信
	private String addr;		// 地址
	private Long sort;		// 权重
	
	public XhsCustomerContact() {
		this(null);
	}


	public XhsCustomerContact(XhsCustomerInfo customerId){
		this.customerId = customerId;
	}
	
	@Length(min=0, max=50, message="客户id长度不能超过 50 个字符")
	public XhsCustomerInfo getCustomerId() {
		return customerId;
	}

	public void setCustomerId(XhsCustomerInfo customerId) {
		this.customerId = customerId;
	}
	
	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=100, message="姓名长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="性别不能为空")
	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=20, message="职务长度不能超过 20 个字符")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Length(min=0, max=20, message="办公电话长度不能超过 20 个字符")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=20, message="移动电话长度不能超过 20 个字符")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Length(min=0, max=50, message="邮箱长度不能超过 50 个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=20, message="QQ长度不能超过 20 个字符")
	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	
	@Length(min=0, max=50, message="微信长度不能超过 50 个字符")
	public String getWebchatNumber() {
		return webchatNumber;
	}

	public void setWebchatNumber(String webchatNumber) {
		this.webchatNumber = webchatNumber;
	}
	
	@Length(min=0, max=100, message="地址长度不能超过 100 个字符")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
}