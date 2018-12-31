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
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 产品库存信息Entity
 * @author liliangming
 * @version 2018-12-23
 */
@Table(name="xhs_stock_info", alias="a", columns={
		@Column(name="id", attrName="id", label="库存id", isPK=true),
		@Column(name="product_id", attrName="product.id", label="产品"),
		@Column(name="warehouse_code", attrName="warehouse.treeCode", label="仓库"),
		@Column(name="quantity", attrName="quantity", label="库存"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable = {
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, attrName = "product", alias = "product", 
					on = "product.id = a.product_id", 
					columns = {@Column(includeEntity = XhsProductInfo.class) }),
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsWarehouseTree.class, attrName = "warehouse", alias = "warehouse", 
			        on = "warehouse.tree_code = a.warehouse_code", 
			        columns = {@Column(includeEntity = XhsWarehouseTree.class) }),
   },orderBy="a.update_date DESC"
)
public class XhsProductStockDetail extends DataEntity<XhsProductStockDetail> {
	
	private static final long serialVersionUID = 1L;
	private XhsProductInfo product;		// 产品
	private XhsWarehouseTree warehouse;		// 仓库
	private Long quantity;		// 库存数量
	
	public XhsProductStockDetail() {
		this(null);
	}

	public XhsProductStockDetail(String id){
		super(id);
	}
	
	public XhsProductInfo getProduct() {
		return product;
	}

	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}

	public XhsWarehouseTree getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(XhsWarehouseTree warehouse) {
		this.warehouse = warehouse;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}