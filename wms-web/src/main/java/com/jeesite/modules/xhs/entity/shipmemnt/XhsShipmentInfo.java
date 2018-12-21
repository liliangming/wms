/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.shipmemnt;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerInfo;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 出货信息Entity
 * 
 * @author liliangming
 * @version 2018-12-18
 */
@Table(name = "xhs_shipment_info", alias = "a", columns = {
		@Column(name = "id", attrName = "id", label = "单号", isPK = true),
		@Column(name = "product_id", attrName = "product.id", label = "产品"),
		@Column(name = "quantity", attrName = "quantity", label = "出货数量"),
		@Column(name = "price", attrName = "price", label = "出货单价"),
		@Column(name = "per", attrName = "per", label = "单位数量"),
		@Column(name = "currency", attrName = "currency", label = "币种"),
		@Column(name = "warehouse_id", attrName = "warehouseInfo.id", label = "仓库"),
		@Column(name = "warehouse_pos_id", attrName = "warehousePosition.id", label = "仓库分区"),
		@Column(name = "customer_id", attrName = "customer.id", label = "客户"),
		@Column(includeEntity = DataEntity.class),
		@Column(name = "shipment_status", attrName = "shipmentStatus", label = "出货状态"), }, joinTable = {
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, alias = "product", on = "product.id = a.product_id", columns = {
						@Column(includeEntity = XhsProductInfo.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsWarehouseTree.class, attrName = "warehouse", alias = "warehouse", on = "warehouse.tree_code = a.warehouse_code", columns = {
						@Column(includeEntity = XhsWarehouseTree.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsCustomerInfo.class, alias = "customer", on = "customer.id = a.customer_id", columns = {
						@Column(includeEntity = XhsCustomerInfo.class) }), }, orderBy = "a.update_date DESC")
public class XhsShipmentInfo extends DataEntity<XhsShipmentInfo> {

	private static final long serialVersionUID = 1L;
	private XhsProductInfo product; // 产品
	private Long quantity; // 出货数量
	private Double price; // 出货单价
	private Long per; // 单位数量
	private String currency; // 币种
	private XhsCustomerInfo customer; // 客户
	private String shipmentStatus; // 出货状态
	private List<XhsShipmentReceiver> xhsShipmentReceiverList = ListUtils.newArrayList(); // 子表列表
	private List<XhsShipmentSender> xhsShipmentSenderList = ListUtils.newArrayList(); // 子表列表
	private List<XhsShipmentLogistics> xhsShipmentLogisticsList = ListUtils.newArrayList(); // 子表列表

	public XhsShipmentInfo() {
		this(null);
	}

	public XhsShipmentInfo(String id) {
		super(id);
	}

	@NotNull(message = "产品不能为空")
	public XhsProductInfo getProduct() {
		return product;
	}

	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}

	@NotNull(message = "出货数量不能为空")
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getPer() {
		return per;
	}

	public void setPer(Long per) {
		this.per = per;
	}

	@Length(min = 0, max = 10, message = "币种长度不能超过 10 个字符")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public XhsCustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(XhsCustomerInfo customer) {
		this.customer = customer;
	}

	@Length(min = 0, max = 50, message = "出货状态长度不能超过 50 个字符")
	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public List<XhsShipmentReceiver> getXhsShipmentReceiverList() {
		return xhsShipmentReceiverList;
	}

	public void setXhsShipmentReceiverList(List<XhsShipmentReceiver> xhsShipmentReceiverList) {
		this.xhsShipmentReceiverList = xhsShipmentReceiverList;
	}

	public List<XhsShipmentSender> getXhsShipmentSenderList() {
		return xhsShipmentSenderList;
	}

	public void setXhsShipmentSenderList(List<XhsShipmentSender> xhsShipmentSenderList) {
		this.xhsShipmentSenderList = xhsShipmentSenderList;
	}

	public List<XhsShipmentLogistics> getXhsShipmentLogisticsList() {
		return xhsShipmentLogisticsList;
	}

	public void setXhsShipmentLogisticsList(List<XhsShipmentLogistics> xhsShipmentLogisticsList) {
		this.xhsShipmentLogisticsList = xhsShipmentLogisticsList;
	}

}