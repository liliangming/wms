/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierInfo;
import com.jeesite.modules.xhs.dao.supplier.XhsSupplierInfoDao;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierContact;
import com.jeesite.modules.xhs.dao.supplier.XhsSupplierContactDao;
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierMail;
import com.jeesite.modules.xhs.dao.supplier.XhsSupplierMailDao;

/**
 * 供应商信息Service
 * @author liliangming
 * @version 2018-12-14
 */
@Service
@Transactional(readOnly=true)
public class XhsSupplierInfoService extends CrudService<XhsSupplierInfoDao, XhsSupplierInfo> {
	
	@Autowired
	private XhsSupplierContactDao xhsSupplierContactDao;
	
	@Autowired
	private XhsSupplierMailDao xhsSupplierMailDao;
	
	/**
	 * 获取单条数据
	 * @param xhsSupplierInfo
	 * @return
	 */
	@Override
	public XhsSupplierInfo get(XhsSupplierInfo xhsSupplierInfo) {
		XhsSupplierInfo entity = super.get(xhsSupplierInfo);
		if (entity != null){
			XhsSupplierContact xhsSupplierContact = new XhsSupplierContact(entity);
			xhsSupplierContact.setStatus(XhsSupplierContact.STATUS_NORMAL);
			entity.setXhsSupplierContactList(xhsSupplierContactDao.findList(xhsSupplierContact));
			XhsSupplierMail xhsSupplierMail = new XhsSupplierMail(entity);
			xhsSupplierMail.setStatus(XhsSupplierMail.STATUS_NORMAL);
			entity.setXhsSupplierMailList(xhsSupplierMailDao.findList(xhsSupplierMail));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param xhsSupplierInfo 查询条件
	 * @param xhsSupplierInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsSupplierInfo> findPage(XhsSupplierInfo xhsSupplierInfo) {
		return super.findPage(xhsSupplierInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsSupplierInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsSupplierInfo xhsSupplierInfo) {
		super.save(xhsSupplierInfo);
		// 保存 XhsSupplierInfo子表
		for (XhsSupplierContact xhsSupplierContact : xhsSupplierInfo.getXhsSupplierContactList()){
			if (!XhsSupplierContact.STATUS_DELETE.equals(xhsSupplierContact.getStatus())){
				xhsSupplierContact.setSupplierId(xhsSupplierInfo);
				if (xhsSupplierContact.getIsNewRecord()){
					xhsSupplierContact.preInsert();
					xhsSupplierContactDao.insert(xhsSupplierContact);
				}else{
					xhsSupplierContact.preUpdate();
					xhsSupplierContactDao.update(xhsSupplierContact);
				}
			}else{
				xhsSupplierContactDao.delete(xhsSupplierContact);
			}
		}
		// 保存 XhsSupplierInfo子表
		for (XhsSupplierMail xhsSupplierMail : xhsSupplierInfo.getXhsSupplierMailList()){
			if (!XhsSupplierMail.STATUS_DELETE.equals(xhsSupplierMail.getStatus())){
				xhsSupplierMail.setSupplierId(xhsSupplierInfo);
				if (xhsSupplierMail.getIsNewRecord()){
					xhsSupplierMail.preInsert();
					xhsSupplierMailDao.insert(xhsSupplierMail);
				}else{
					xhsSupplierMail.preUpdate();
					xhsSupplierMailDao.update(xhsSupplierMail);
				}
			}else{
				xhsSupplierMailDao.delete(xhsSupplierMail);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param xhsSupplierInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsSupplierInfo xhsSupplierInfo) {
		super.updateStatus(xhsSupplierInfo);
	}
	
	/**
	 * 删除数据
	 * @param xhsSupplierInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsSupplierInfo xhsSupplierInfo) {
		super.delete(xhsSupplierInfo);
		XhsSupplierContact xhsSupplierContact = new XhsSupplierContact();
		xhsSupplierContact.setSupplierId(xhsSupplierInfo);
		xhsSupplierContactDao.delete(xhsSupplierContact);
		XhsSupplierMail xhsSupplierMail = new XhsSupplierMail();
		xhsSupplierMail.setSupplierId(xhsSupplierInfo);
		xhsSupplierMailDao.delete(xhsSupplierMail);
	}
	
}