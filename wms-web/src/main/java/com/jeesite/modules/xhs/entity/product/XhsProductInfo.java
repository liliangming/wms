/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.product;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 产品信息Entity
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Table(name = "xhs_product_info", alias = "a", columns = {
		@Column(name = "id", attrName = "id", label = "主键", isPK = true),
		@Column(name = "product_code", attrName = "productCode", label = "产品编码", queryType = QueryType.LIKE),
		@Column(name = "product_name", attrName = "productName", label = "产品名称", queryType = QueryType.LIKE),
		@Column(name = "sell_price", attrName = "sellPrice", label = "销售单价"),
		@Column(name = "purchase_price", attrName = "purchasePrice", label = "采购单价"),
		@Column(name = "per", attrName = "per", label = "per"),
		@Column(name = "currency", attrName = "currency", label = "币种"),
		@Column(includeEntity = DataEntity.class), }, orderBy = "a.update_date DESC")
public class XhsProductInfo extends DataEntity<XhsProductInfo> {

	private static final long serialVersionUID = 1L;
	private String productCode; // 产品编码
	private String productName; // 产品名称
	private Double sellPrice; // 销售单价
	private Double purchasePrice; // 采购单价
	private Long per; // per
	private String currency; // 币种

	public XhsProductInfo() {
		this(null);
	}

	public XhsProductInfo(String id) {
		super(id);
	}

	@NotBlank(message = "产品编码不能为空")
	@Length(min = 0, max = 20, message = "产品编码长度不能超过 20 个字符")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@NotBlank(message = "产品名称不能为空")
	@Length(min = 0, max = 100, message = "产品名称长度不能超过 100 个字符")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
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
}