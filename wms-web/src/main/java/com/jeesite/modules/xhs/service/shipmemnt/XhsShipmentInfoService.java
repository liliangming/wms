/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.shipmemnt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.entity.shipmemnt.XhsShipmentInfo;
import com.jeesite.modules.xhs.dao.shipmemnt.XhsShipmentInfoDao;
import com.jeesite.modules.xhs.entity.shipmemnt.XhsShipmentReceiver;
import com.jeesite.modules.xhs.dao.shipmemnt.XhsShipmentReceiverDao;
import com.jeesite.modules.xhs.entity.shipmemnt.XhsShipmentSender;
import com.jeesite.modules.xhs.dao.shipmemnt.XhsShipmentSenderDao;
import com.jeesite.modules.xhs.entity.shipmemnt.XhsShipmentLogistics;
import com.jeesite.modules.xhs.dao.shipmemnt.XhsShipmentLogisticsDao;

/**
 * 出货信息Service
 * @author liliangming
 * @version 2018-12-18
 */
@Service
@Transactional(readOnly=true)
public class XhsShipmentInfoService extends CrudService<XhsShipmentInfoDao, XhsShipmentInfo> {
	
	@Autowired
	private XhsShipmentReceiverDao xhsShipmentReceiverDao;
	
	@Autowired
	private XhsShipmentSenderDao xhsShipmentSenderDao;
	
	@Autowired
	private XhsShipmentLogisticsDao xhsShipmentLogisticsDao;
	
	/**
	 * 获取单条数据
	 * @param xhsShipmentInfo
	 * @return
	 */
	@Override
	public XhsShipmentInfo get(XhsShipmentInfo xhsShipmentInfo) {
		XhsShipmentInfo entity = super.get(xhsShipmentInfo);
		if (entity != null){
			XhsShipmentReceiver xhsShipmentReceiver = new XhsShipmentReceiver(entity);
			xhsShipmentReceiver.setStatus(XhsShipmentReceiver.STATUS_NORMAL);
			entity.setXhsShipmentReceiverList(xhsShipmentReceiverDao.findList(xhsShipmentReceiver));
			XhsShipmentSender xhsShipmentSender = new XhsShipmentSender(entity);
			xhsShipmentSender.setStatus(XhsShipmentSender.STATUS_NORMAL);
			entity.setXhsShipmentSenderList(xhsShipmentSenderDao.findList(xhsShipmentSender));
			XhsShipmentLogistics xhsShipmentLogistics = new XhsShipmentLogistics(entity);
			xhsShipmentLogistics.setStatus(XhsShipmentLogistics.STATUS_NORMAL);
			entity.setXhsShipmentLogisticsList(xhsShipmentLogisticsDao.findList(xhsShipmentLogistics));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param xhsShipmentInfo 查询条件
	 * @param xhsShipmentInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsShipmentInfo> findPage(XhsShipmentInfo xhsShipmentInfo) {
		return super.findPage(xhsShipmentInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsShipmentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsShipmentInfo xhsShipmentInfo) {
		super.save(xhsShipmentInfo);
		// 保存 XhsShipmentInfo子表
		for (XhsShipmentReceiver xhsShipmentReceiver : xhsShipmentInfo.getXhsShipmentReceiverList()){
			if (!XhsShipmentReceiver.STATUS_DELETE.equals(xhsShipmentReceiver.getStatus())){
				xhsShipmentReceiver.setShipmentId(xhsShipmentInfo);
				if (xhsShipmentReceiver.getIsNewRecord()){
					xhsShipmentReceiver.preInsert();
					xhsShipmentReceiverDao.insert(xhsShipmentReceiver);
				}else{
					xhsShipmentReceiver.preUpdate();
					xhsShipmentReceiverDao.update(xhsShipmentReceiver);
				}
			}else{
				xhsShipmentReceiverDao.delete(xhsShipmentReceiver);
			}
		}
		// 保存 XhsShipmentInfo子表
		for (XhsShipmentSender xhsShipmentSender : xhsShipmentInfo.getXhsShipmentSenderList()){
			if (!XhsShipmentSender.STATUS_DELETE.equals(xhsShipmentSender.getStatus())){
				xhsShipmentSender.setShipmentId(xhsShipmentInfo);
				if (xhsShipmentSender.getIsNewRecord()){
					xhsShipmentSender.preInsert();
					xhsShipmentSenderDao.insert(xhsShipmentSender);
				}else{
					xhsShipmentSender.preUpdate();
					xhsShipmentSenderDao.update(xhsShipmentSender);
				}
			}else{
				xhsShipmentSenderDao.delete(xhsShipmentSender);
			}
		}
		// 保存 XhsShipmentInfo子表
		for (XhsShipmentLogistics xhsShipmentLogistics : xhsShipmentInfo.getXhsShipmentLogisticsList()){
			if (!XhsShipmentLogistics.STATUS_DELETE.equals(xhsShipmentLogistics.getStatus())){
				xhsShipmentLogistics.setShipmentId(xhsShipmentInfo);
				if (xhsShipmentLogistics.getIsNewRecord()){
					xhsShipmentLogistics.preInsert();
					xhsShipmentLogisticsDao.insert(xhsShipmentLogistics);
				}else{
					xhsShipmentLogistics.preUpdate();
					xhsShipmentLogisticsDao.update(xhsShipmentLogistics);
				}
			}else{
				xhsShipmentLogisticsDao.delete(xhsShipmentLogistics);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param xhsShipmentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsShipmentInfo xhsShipmentInfo) {
		super.updateStatus(xhsShipmentInfo);
	}
	
	/**
	 * 删除数据
	 * @param xhsShipmentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsShipmentInfo xhsShipmentInfo) {
		super.delete(xhsShipmentInfo);
		XhsShipmentReceiver xhsShipmentReceiver = new XhsShipmentReceiver();
		xhsShipmentReceiver.setShipmentId(xhsShipmentInfo);
		xhsShipmentReceiverDao.delete(xhsShipmentReceiver);
		XhsShipmentSender xhsShipmentSender = new XhsShipmentSender();
		xhsShipmentSender.setShipmentId(xhsShipmentInfo);
		xhsShipmentSenderDao.delete(xhsShipmentSender);
		XhsShipmentLogistics xhsShipmentLogistics = new XhsShipmentLogistics();
		xhsShipmentLogistics.setShipmentId(xhsShipmentInfo);
		xhsShipmentLogisticsDao.delete(xhsShipmentLogistics);
	}
	
}