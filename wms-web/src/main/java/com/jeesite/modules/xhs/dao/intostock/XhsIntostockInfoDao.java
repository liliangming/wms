/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.intostock;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockInfo;

/**
 * 入库登记信息DAO接口
 * 
 * @author liliangming
 * @version 2018-12-23
 */
@MyBatisDao
public interface XhsIntostockInfoDao extends CrudDao<XhsIntostockInfo> {

	/**
	 * 修改登记状态
	 * 
	 * @param id
	 * @param intostockStatus
	 * @return
	 */
	int updateIntostockStatus(@Param("id") String id, @Param("intostockStatus") String intostockStatus,
			@Param("updateBy") String updateBy);
}