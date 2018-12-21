/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.customer;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerInfo;

/**
 * 客户信息DAO接口
 * @author liliangming
 * @version 2018-12-14
 */
@MyBatisDao
public interface XhsCustomerInfoDao extends CrudDao<XhsCustomerInfo> {
	
}