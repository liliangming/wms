/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.intostock;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;

/**
 * 入库登记信息Entity
 * @author liliangming
 * @version 2018-12-23
 */
@Table(name="xhs_intostock_product", alias="a", columns={
		@Column(name="id", attrName="id", label="入库单号", isPK=true),
		@Column(name="intostock_id", attrName="intostockId.id", label="入库单"),
		@Column(name="product_id", attrName="product.id", label="产品"),
		@Column(name="product_id", attrName="productId", label="产品id"),
		@Column(name="quantity", attrName="quantity", label="数量"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, attrName = "product", alias = "product", 
					on = "product.id = a.product_id", 
					columns = {@Column(includeEntity = XhsProductInfo.class) }),
	}, orderBy="a.create_date ASC"
)
public class XhsIntostockProduct extends DataEntity<XhsIntostockProduct> {
	
	private static final long serialVersionUID = 1L;
	private XhsIntostockInfo intostockId;		// 入库单 父类
	private XhsProductInfo product;		// 产品
	private String productId;		// 产品
	private String productName;		// 产品
	private Long quantity;		// 数量
	
	public XhsIntostockProduct() {
		this(null);
	}


	public XhsIntostockProduct(XhsIntostockInfo intostockId){
		this.intostockId = intostockId;
	}
	
	@Length(min=0, max=50, message="入库单长度不能超过 50 个字符")
	public XhsIntostockInfo getIntostockId() {
		return intostockId;
	}

	public void setIntostockId(XhsIntostockInfo intostockId) {
		this.intostockId = intostockId;
	}
	
	public XhsProductInfo getProduct() {
		return product;
	}


	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}


	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		if( productName == null )
		{
			productName = product.getProductName() == null ? "" : product.getProductName();
		}
		
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}