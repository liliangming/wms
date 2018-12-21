/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouseinput;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierInfo;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 入库登记Entity
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Table(name = "xhs_warehouse_input", alias = "a", columns = {
		@Column(name = "id", attrName = "id", label = "入库单号", isPK = true),
		@Column(name = "supplier_id", attrName = "supplier.id", label = "供应商id"),
		@Column(name = "product_id", attrName = "product.id", label = "产品id"),
		@Column(name = "input_price", attrName = "inputPrice", label = "入库单价"),
		@Column(name = "currency", attrName = "currency", label = "币种"),
		@Column(name = "input_per", attrName = "inputPer", label = "入库单位数量"),
		@Column(name = "input_quantity", attrName = "inputQuantity", label = "入库数量"),
		@Column(name = "warehouse_code", attrName = "warehouse.treeCode", label = "仓库或分区编码"),
		@Column(includeEntity = DataEntity.class), }, joinTable = {
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, attrName = "product", alias = "product", on = "product.id = a.product_id", columns = {
						@Column(includeEntity = XhsProductInfo.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsWarehouseTree.class, attrName = "warehouse", alias = "warehouse", on = "warehouse.tree_code = a.warehouse_code", columns = {
						@Column(includeEntity = XhsWarehouseTree.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsSupplierInfo.class, attrName = "supplier", alias = "supplier", on = "supplier.id = a.supplier_id", columns = {
						@Column(includeEntity = XhsSupplierInfo.class) }), }, orderBy = "a.update_date DESC")
public class XhsWarehouseInput extends DataEntity<XhsWarehouseInput> {

	private static final long serialVersionUID = 1L;
	private XhsSupplierInfo supplier; // 供应商
	private XhsProductInfo product; // 产品
	private XhsWarehouseTree warehouse; // 入库仓库
	private Double inputPrice; // 入库单价
	private String currency; // 币种
	private Long inputPer; // 入库单位数量
	private Long inputQuantity; // 入库数量

	private String inputPriceDesc;

	public XhsWarehouseInput() {
		this(null);
	}

	public XhsWarehouseInput(String id) {
		super(id);
	}

	public Double getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(Double inputPrice) {
		this.inputPrice = inputPrice;
	}

	@Length(min = 0, max = 10, message = "币种长度不能超过 10 个字符")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getInputPer() {
		return inputPer;
	}

	public void setInputPer(Long inputPer) {
		this.inputPer = inputPer;
	}

	@NotNull(message = "入库数量不能为空")
	public Long getInputQuantity() {
		return inputQuantity;
	}

	public void setInputQuantity(Long inputQuantity) {
		this.inputQuantity = inputQuantity;
	}

	public XhsSupplierInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(XhsSupplierInfo supplier) {
		this.supplier = supplier;
	}

	public XhsProductInfo getProduct() {
		return product;
	}

	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}

	public String getInputPriceDesc() {
		if (inputPriceDesc == null) {
			inputPriceDesc = buildInputPriceDesc();
		}
		return inputPriceDesc;
	}

	private String buildInputPriceDesc() {
		if (this.inputPrice == null) {
			return "";
		}

		return String.format("%s元/%s个", this.inputPrice, this.inputPer == null ? 1 : this.inputPer);
	}

	public XhsWarehouseTree getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(XhsWarehouseTree warehouse) {
		this.warehouse = warehouse;
	}
}