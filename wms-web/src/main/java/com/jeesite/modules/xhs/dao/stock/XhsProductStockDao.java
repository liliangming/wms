/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.stock.XhsProductStock;
import com.jeesite.modules.xhs.entity.stock.XhsProductStockBO;

/**
 * 产品库存信息DAO接口
 * 
 * @author liliangming
 * @version 2018-12-23
 */
@MyBatisDao
public interface XhsProductStockDao extends CrudDao<XhsProductStock> {

	int addXhsProductStock(XhsProductStock xhsProductStock);

	/**
	 * 查询产品库存数据
	 * 
	 * @param productId
	 * @param warehouseCode
	 * @return
	 */
	List<XhsProductStockBO> queryXhsProductStock(@Param("productId") String productId,
			@Param("warehouseCode") String warehouseCode);
}