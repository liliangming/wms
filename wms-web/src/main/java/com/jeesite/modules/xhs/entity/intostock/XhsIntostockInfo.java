/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.intostock;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierInfo;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 入库登记信息Entity
 * @author liliangming
 * @version 2018-12-23
 */
@Table(name="xhs_intostock_info", alias="a", columns={
		@Column(name="id", attrName="id", label="入库单号", isPK=true),
		@Column(name="supplier_id", attrName="supplier.id", label="供应商"),
		@Column(name="warehouse_code", attrName="warehouse.treeCode", label="仓库"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="instock_type", attrName="instockType", label="入库类型"),
		@Column(name="instock_status", attrName="instockStatus", label="入库状态"),
		@Column(name="product_num", attrName="productNum", label="入库产品种数"),
		@Column(name="quantity", attrName="quantity", label="入库产品总类数"),
	}, joinTable = {
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsWarehouseTree.class, attrName = "warehouse", alias = "warehouse", 
					on = "warehouse.tree_code = a.warehouse_code", 
					columns = {@Column(includeEntity = XhsWarehouseTree.class) }),
			@JoinTable(type = Type.LEFT_JOIN, entity = XhsSupplierInfo.class, attrName = "supplier", alias = "supplier", 
			on = "supplier.id = a.supplier_id", 
			columns = {@Column(includeEntity = XhsSupplierInfo.class) }), 
	},orderBy="a.update_date DESC"
)
public class XhsIntostockInfo extends DataEntity<XhsIntostockInfo> {
	
	private static final long serialVersionUID = 1L;
	private XhsSupplierInfo supplier;		// 供应商
	private XhsWarehouseTree warehouse;		// 仓库
	private String instockType;		// 入库类型
	private String instockStatus;		// 入库状态
	private Long productNum; //入库产品种数
	private Long quantity; //入库产品总数量
	private List<XhsIntostockProduct> xhsIntostockProductList = ListUtils.newArrayList();		// 子表列表
	
	public XhsIntostockInfo() {
		this(null);
	}

	public XhsIntostockInfo(String id){
		super(id);
	}
	
	public XhsSupplierInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(XhsSupplierInfo supplier) {
		this.supplier = supplier;
	}

	public XhsWarehouseTree getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(XhsWarehouseTree warehouse) {
		this.warehouse = warehouse;
	}

	@NotBlank(message="入库类型不能为空")
	@Length(min=0, max=20, message="入库类型长度不能超过 20 个字符")
	public String getInstockType() {
		return instockType;
	}

	public void setInstockType(String instockType) {
		this.instockType = instockType;
	}
	
	@Length(min=0, max=20, message="入库状态长度不能超过 20 个字符")
	public String getInstockStatus() {
		return instockStatus;
	}

	public void setInstockStatus(String instockStatus) {
		this.instockStatus = instockStatus;
	}
	
	public List<XhsIntostockProduct> getXhsIntostockProductList() {
		return xhsIntostockProductList;
	}

	public void setXhsIntostockProductList(List<XhsIntostockProduct> xhsIntostockProductList) {
		this.xhsIntostockProductList = xhsIntostockProductList;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}