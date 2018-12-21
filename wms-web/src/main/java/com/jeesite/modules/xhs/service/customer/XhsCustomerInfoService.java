/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerInfo;
import com.jeesite.modules.xhs.dao.customer.XhsCustomerInfoDao;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerContact;
import com.jeesite.modules.xhs.dao.customer.XhsCustomerContactDao;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerMail;
import com.jeesite.modules.xhs.dao.customer.XhsCustomerMailDao;

/**
 * 客户信息Service
 * @author liliangming
 * @version 2018-12-14
 */
@Service
@Transactional(readOnly=true)
public class XhsCustomerInfoService extends CrudService<XhsCustomerInfoDao, XhsCustomerInfo> {
	
	@Autowired
	private XhsCustomerContactDao xhsCustomerContactDao;
	
	@Autowired
	private XhsCustomerMailDao xhsCustomerMailDao;
	
	/**
	 * 获取单条数据
	 * @param xhsCustomerInfo
	 * @return
	 */
	@Override
	public XhsCustomerInfo get(XhsCustomerInfo xhsCustomerInfo) {
		XhsCustomerInfo entity = super.get(xhsCustomerInfo);
		if (entity != null){
			XhsCustomerContact xhsCustomerContact = new XhsCustomerContact(entity);
			xhsCustomerContact.setStatus(XhsCustomerContact.STATUS_NORMAL);
			entity.setXhsCustomerContactList(xhsCustomerContactDao.findList(xhsCustomerContact));
			XhsCustomerMail xhsCustomerMail = new XhsCustomerMail(entity);
			xhsCustomerMail.setStatus(XhsCustomerMail.STATUS_NORMAL);
			entity.setXhsCustomerMailList(xhsCustomerMailDao.findList(xhsCustomerMail));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param xhsCustomerInfo 查询条件
	 * @param xhsCustomerInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsCustomerInfo> findPage(XhsCustomerInfo xhsCustomerInfo) {
		return super.findPage(xhsCustomerInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsCustomerInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsCustomerInfo xhsCustomerInfo) {
		super.save(xhsCustomerInfo);
		// 保存 XhsCustomerInfo子表
		for (XhsCustomerContact xhsCustomerContact : xhsCustomerInfo.getXhsCustomerContactList()){
			if (!XhsCustomerContact.STATUS_DELETE.equals(xhsCustomerContact.getStatus())){
				xhsCustomerContact.setCustomerId(xhsCustomerInfo);
				if (xhsCustomerContact.getIsNewRecord()){
					xhsCustomerContact.preInsert();
					xhsCustomerContactDao.insert(xhsCustomerContact);
				}else{
					xhsCustomerContact.preUpdate();
					xhsCustomerContactDao.update(xhsCustomerContact);
				}
			}else{
				xhsCustomerContactDao.delete(xhsCustomerContact);
			}
		}
		// 保存 XhsCustomerInfo子表
		for (XhsCustomerMail xhsCustomerMail : xhsCustomerInfo.getXhsCustomerMailList()){
			if (!XhsCustomerMail.STATUS_DELETE.equals(xhsCustomerMail.getStatus())){
				xhsCustomerMail.setCustomerId(xhsCustomerInfo);
				if (xhsCustomerMail.getIsNewRecord()){
					xhsCustomerMail.preInsert();
					xhsCustomerMailDao.insert(xhsCustomerMail);
				}else{
					xhsCustomerMail.preUpdate();
					xhsCustomerMailDao.update(xhsCustomerMail);
				}
			}else{
				xhsCustomerMailDao.delete(xhsCustomerMail);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param xhsCustomerInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsCustomerInfo xhsCustomerInfo) {
		super.updateStatus(xhsCustomerInfo);
	}
	
	/**
	 * 删除数据
	 * @param xhsCustomerInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsCustomerInfo xhsCustomerInfo) {
		super.delete(xhsCustomerInfo);
		XhsCustomerContact xhsCustomerContact = new XhsCustomerContact();
		xhsCustomerContact.setCustomerId(xhsCustomerInfo);
		xhsCustomerContactDao.delete(xhsCustomerContact);
		XhsCustomerMail xhsCustomerMail = new XhsCustomerMail();
		xhsCustomerMail.setCustomerId(xhsCustomerInfo);
		xhsCustomerMailDao.delete(xhsCustomerMail);
	}
	
}