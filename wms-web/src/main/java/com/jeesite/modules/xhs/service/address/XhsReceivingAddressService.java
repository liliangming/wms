/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.address;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.entity.address.XhsReceivingAddress;
import com.jeesite.modules.xhs.dao.address.XhsReceivingAddressDao;

/**
 * 收货地址信息Service
 * @author liliangming
 * @version 2018-12-18
 */
@Service
@Transactional(readOnly=true)
public class XhsReceivingAddressService extends CrudService<XhsReceivingAddressDao, XhsReceivingAddress> {
	
	/**
	 * 获取单条数据
	 * @param xhsReceivingAddress
	 * @return
	 */
	@Override
	public XhsReceivingAddress get(XhsReceivingAddress xhsReceivingAddress) {
		return super.get(xhsReceivingAddress);
	}
	
	/**
	 * 查询分页数据
	 * @param xhsReceivingAddress 查询条件
	 * @param xhsReceivingAddress.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsReceivingAddress> findPage(XhsReceivingAddress xhsReceivingAddress) {
		return super.findPage(xhsReceivingAddress);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsReceivingAddress
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsReceivingAddress xhsReceivingAddress) {
		super.save(xhsReceivingAddress);
	}
	
	/**
	 * 更新状态
	 * @param xhsReceivingAddress
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsReceivingAddress xhsReceivingAddress) {
		super.updateStatus(xhsReceivingAddress);
	}
	
	/**
	 * 删除数据
	 * @param xhsReceivingAddress
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsReceivingAddress xhsReceivingAddress) {
		super.delete(xhsReceivingAddress);
	}
	
}