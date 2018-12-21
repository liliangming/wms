/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.supplier;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierInfo;

/**
 * 供应商信息DAO接口
 * @author liliangming
 * @version 2018-12-14
 */
@MyBatisDao
public interface XhsSupplierInfoDao extends CrudDao<XhsSupplierInfo> {
	
}