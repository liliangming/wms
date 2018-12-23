/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.stock;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.stock.XhsProductStock;

/**
 * 产品库存信息DAO接口
 * @author liliangming
 * @version 2018-12-23
 */
@MyBatisDao
public interface XhsProductStockDao extends CrudDao<XhsProductStock> {
	
	int addXhsProductStock(XhsProductStock xhsProductStock);
}