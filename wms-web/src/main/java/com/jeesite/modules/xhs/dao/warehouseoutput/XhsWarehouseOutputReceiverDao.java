/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.warehouseoutput;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputReceiver;

/**
 * 出库信息DAO接口
 * 
 * @author liliangming
 * @version 2018-12-20
 */
@MyBatisDao
public interface XhsWarehouseOutputReceiverDao extends CrudDao<XhsWarehouseOutputReceiver> {

//	void updateReceiver(@Param("outputId") String outputId, @Param("receiverId") String receiverId,
//			@Param("receiverName") String receiverName, @Param("receiverAddr") String receiverAddr,
//			@Param("receiverPhone") String receiverPhone);
}