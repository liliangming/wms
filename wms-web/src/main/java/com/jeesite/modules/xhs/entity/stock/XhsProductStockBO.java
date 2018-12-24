/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.stock;

import com.jeesite.common.entity.DataEntity;

/**
 * 产品库存信息bo
 * 
 * @author liliangming
 * @version 2018-12-23
 */
public class XhsProductStockBO extends DataEntity<XhsProductStockBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2653033078892170569L;

	private String productId; // 产品Id

	private String productCode; // 产品Code

	private String productName; // 产品名称

	private Long quantity; // 库存数量

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}