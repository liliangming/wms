/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.warehouseoutput;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputLogistics;

/**
 * 出库信息DAO接口
 * @author liliangming
 * @version 2018-12-20
 */
@MyBatisDao
public interface XhsWarehouseOutputLogisticsDao extends CrudDao<XhsWarehouseOutputLogistics> {
	
//	void updateLogistics(@Param("outputId") String outputId, @Param("logisticsId") String logisticsId,
//			@Param("mailType") String mailType, @Param("mailNumber") String mailNumber,
//			@Param("logisticsRemarks") String logisticsRemarks);
}