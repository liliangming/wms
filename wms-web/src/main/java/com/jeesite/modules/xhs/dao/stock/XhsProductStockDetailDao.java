/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.stock;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.stock.XhsProductStockDetail;

/**
 * 产品库存信息DAO接口
 * 
 * @author liliangming
 * @version 2018-12-23
 */
@MyBatisDao
public interface XhsProductStockDetailDao extends CrudDao<XhsProductStockDetail> {

	int addXhsProductStockDetail(XhsProductStockDetail xhsProductStockDetail);

	Long getQuantityByProductIdAndWarehouseCode(@Param("productId") String productId,
			@Param("treeCode") String treeCode);
}