/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.supplier;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 供应商信息Entity
 * @author liliangming
 * @version 2018-12-14
 */
@Table(name="xhs_supplier_info", alias="a", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="name", attrName="name", label="供应商名称", queryType=QueryType.LIKE),
		@Column(name="addr", attrName="addr", label="供应商地址"),
		@Column(name="phone_number", attrName="phoneNumber", label="办公电话"),
		@Column(name="mobile_number", attrName="mobileNumber", label="移动电话"),
		@Column(name="email", attrName="email", label="电子邮箱"),
		@Column(name="qq_number", attrName="qqNumber", label="企业QQ"),
		@Column(name="webchat_number", attrName="webchatNumber", label="企业微信"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="sort", attrName="sort", label="权重"),
	}, orderBy="a.sort DESC"
)
public class XhsSupplierInfo extends DataEntity<XhsSupplierInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 供应商名称
	private String addr;		// 供应商地址
	private String phoneNumber;		// 办公电话
	private String mobileNumber;		// 移动电话
	private String email;		// 电子邮箱
	private String qqNumber;		// 企业QQ
	private String webchatNumber;		// 企业微信
	private Long sort;		// 权重
	private List<XhsSupplierContact> xhsSupplierContactList = ListUtils.newArrayList();		// 子表列表
	private List<XhsSupplierMail> xhsSupplierMailList = ListUtils.newArrayList();		// 子表列表
	
	public XhsSupplierInfo() {
		this(null);
	}

	public XhsSupplierInfo(String id){
		super(id);
	}
	
	@NotBlank(message="供应商名称不能为空")
	@Length(min=0, max=100, message="供应商名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="供应商地址不能为空")
	@Length(min=0, max=100, message="供应商地址长度不能超过 100 个字符")
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
	
	@Length(min=0, max=50, message="电子邮箱长度不能超过 50 个字符")
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
	
	@Length(min=0, max=20, message="企业微信长度不能超过 20 个字符")
	public String getWebchatNumber() {
		return webchatNumber;
	}

	public void setWebchatNumber(String webchatNumber) {
		this.webchatNumber = webchatNumber;
	}
	
	@NotNull(message="权重不能为空")
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public List<XhsSupplierContact> getXhsSupplierContactList() {
		return xhsSupplierContactList;
	}

	public void setXhsSupplierContactList(List<XhsSupplierContact> xhsSupplierContactList) {
		this.xhsSupplierContactList = xhsSupplierContactList;
	}
	
	public List<XhsSupplierMail> getXhsSupplierMailList() {
		return xhsSupplierMailList;
	}

	public void setXhsSupplierMailList(List<XhsSupplierMail> xhsSupplierMailList) {
		this.xhsSupplierMailList = xhsSupplierMailList;
	}
	
}