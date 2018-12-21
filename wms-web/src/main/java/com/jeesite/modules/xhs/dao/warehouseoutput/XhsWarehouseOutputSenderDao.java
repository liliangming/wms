/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.warehouseoutput;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputSender;

/**
 * 出库信息DAO接口
 * @author liliangming
 * @version 2018-12-20
 */
@MyBatisDao
public interface XhsWarehouseOutputSenderDao extends CrudDao<XhsWarehouseOutputSender> {
	
//	void updateSender(@Param("outputId") String outputId, @Param("senderId") String senderId,
//			@Param("senderName") String senderName, @Param("senderAddr") String senderAddr,
//			@Param("senderPhone") String senderPhone);
}