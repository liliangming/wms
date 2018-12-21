/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.warehouse;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 仓库信息DAO接口
 * @author liliangming
 * @version 2018-12-19
 */
@MyBatisDao
public interface XhsWarehouseTreeDao extends TreeDao<XhsWarehouseTree> {
	
}