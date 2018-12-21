/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.address;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.address.XhsReceivingAddress;

/**
 * 收货地址信息DAO接口
 * @author liliangming
 * @version 2018-12-18
 */
@MyBatisDao
public interface XhsReceivingAddressDao extends CrudDao<XhsReceivingAddress> {
	
}