/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.customer;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 客户信息Entity
 * @author liliangming
 * @version 2018-12-14
 */
@Table(name="xhs_customer_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="客户名称", queryType=QueryType.LIKE),
		@Column(name="addr", attrName="addr", label="客户地址"),
		@Column(name="phone_number", attrName="phoneNumber", label="办公电话"),
		@Column(name="mobile_number", attrName="mobileNumber", label="移动电话"),
		@Column(name="email", attrName="email", label="电子邮件"),
		@Column(name="qq_number", attrName="qqNumber", label="企业QQ"),
		@Column(name="webchat_number", attrName="webchatNumber", label="企业微信"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="sort", attrName="sort", label="权重"),
	}, orderBy="a.update_date DESC"
)
public class XhsCustomerInfo extends DataEntity<XhsCustomerInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 客户名称
	private String addr;		// 客户地址
	private String phoneNumber;		// 办公电话
	private String mobileNumber;		// 移动电话
	private String email;		// 电子邮件
	private String qqNumber;		// 企业QQ
	private String webchatNumber;		// 企业微信
	private Long sort;		// 权重
	private List<XhsCustomerContact> xhsCustomerContactList = ListUtils.newArrayList();		// 子表列表
	private List<XhsCustomerMail> xhsCustomerMailList = ListUtils.newArrayList();		// 子表列表
	
	public XhsCustomerInfo() {
		this(null);
	}

	public XhsCustomerInfo(String id){
		super(id);
	}
	
	@NotBlank(message="客户名称不能为空")
	@Length(min=0, max=100, message="客户名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="客户地址不能为空")
	@Length(min=0, max=200, message="客户地址长度不能超过 200 个字符")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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
	
	@Length(min=0, max=50, message="电子邮件长度不能超过 50 个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=20, message="企业QQ长度不能超过 20 个字符")
	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	
	@Length(min=0, max=50, message="企业微信长度不能超过 50 个字符")
	public String getWebchatNumber() {
		return webchatNumber;
	}

	public void setWebchatNumber(String webchatNumber) {
		this.webchatNumber = webchatNumber;
	}
	
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public List<XhsCustomerContact> getXhsCustomerContactList() {
		return xhsCustomerContactList;
	}

	public void setXhsCustomerContactList(List<XhsCustomerContact> xhsCustomerContactList) {
		this.xhsCustomerContactList = xhsCustomerContactList;
	}
	
	public List<XhsCustomerMail> getXhsCustomerMailList() {
		return xhsCustomerMailList;
	}

	public void setXhsCustomerMailList(List<XhsCustomerMail> xhsCustomerMailList) {
		this.xhsCustomerMailList = xhsCustomerMailList;
	}
	
}