/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.stock;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;

/**
 * 产品库存信息Entity
 * @author liliangming
 * @version 2018-12-23
 */
@Table(name="xhs_stock_info", alias="a", columns={
		@Column(name="id", attrName="id", label="库存id", isPK=true),
		@Column(name="product_id", attrName="product.id", label="产品"),
		@Column(name="warehouse_code", attrName="warehouseCode", label="仓库编码"),
		@Column(name="quantity", attrName="quantity", label="库存总量"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, attrName = "product", alias = "product", 
					on = "product.id = a.product_id", 
					columns = {@Column(includeEntity = XhsProductInfo.class) }),
   }, orderBy="a.update_date DESC"
)
public class XhsProductStock extends DataEntity<XhsProductStock> {
	
	private static final long serialVersionUID = 1L;
	private XhsProductInfo product;		// 产品
	private String warehouseCode;		// 仓库Code
	private Long quantity;		// 库存数量
	
	public XhsProductStock() {
		this(null);
	}

	public XhsProductStock(String id){
		super(id);
	}
	
	public XhsProductInfo getProduct() {
		return product;
	}

	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}