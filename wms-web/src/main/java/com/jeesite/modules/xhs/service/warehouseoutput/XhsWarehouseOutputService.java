/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.warehouseoutput;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.xhs.dao.warehouseoutput.XhsWarehouseOutputDao;
import com.jeesite.modules.xhs.dao.warehouseoutput.XhsWarehouseOutputLogisticsDao;
import com.jeesite.modules.xhs.dao.warehouseoutput.XhsWarehouseOutputReceiverDao;
import com.jeesite.modules.xhs.dao.warehouseoutput.XhsWarehouseOutputSenderDao;
import com.jeesite.modules.xhs.entity.warehouseoutput.LogisticsInfoResultBO;
import com.jeesite.modules.xhs.entity.warehouseoutput.QueryLogisticsResponseBO;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutput;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputLogistics;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputLogisticsBO;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputReceiver;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputSender;
import com.jeesite.modules.xhs.util.CoderUtils;
import com.jeesite.modules.xhs.util.FastJsonUtil;
import com.jeesite.modules.xhs.util.IOUtil;

/**
 * 出库信息Service
 * 
 * @author liliangming
 * @version 2018-12-20
 */
@Service
@Transactional(readOnly = true)
public class XhsWarehouseOutputService extends CrudService<XhsWarehouseOutputDao, XhsWarehouseOutput> {

	@Autowired
	private XhsWarehouseOutputReceiverDao xhsWarehouseOutputReceiverDao;

	@Autowired
	private XhsWarehouseOutputSenderDao xhsWarehouseOutputSenderDao;

	@Autowired
	private XhsWarehouseOutputLogisticsDao xhsWarehouseOutputLogisticsDao;

	private CloseableHttpClient httpclient = HttpClients.createDefault();;

	private final static String URL_QUERY_LOGISTICS = "http://www.kuaidi100.com/query?type=%s&postid=%s";

	/**
	 * 获取单条数据
	 * 
	 * @param xhsWarehouseOutput
	 * @return
	 */
	@Override
	public XhsWarehouseOutput get(XhsWarehouseOutput xhsWarehouseOutput) {
		XhsWarehouseOutput entity = super.get(xhsWarehouseOutput);
		if (entity != null) {
			XhsWarehouseOutputReceiver xhsWarehouseOutputReceiver = new XhsWarehouseOutputReceiver(entity);
			xhsWarehouseOutputReceiver.setStatus(XhsWarehouseOutputReceiver.STATUS_NORMAL);
			entity.setXhsWarehouseOutputReceiverList(
					xhsWarehouseOutputReceiverDao.findList(xhsWarehouseOutputReceiver));
			XhsWarehouseOutputSender xhsWarehouseOutputSender = new XhsWarehouseOutputSender(entity);
			xhsWarehouseOutputSender.setStatus(XhsWarehouseOutputSender.STATUS_NORMAL);
			entity.setXhsWarehouseOutputSenderList(xhsWarehouseOutputSenderDao.findList(xhsWarehouseOutputSender));
			XhsWarehouseOutputLogistics xhsWarehouseOutputLogistics = new XhsWarehouseOutputLogistics(entity);
			xhsWarehouseOutputLogistics.setStatus(XhsWarehouseOutputLogistics.STATUS_NORMAL);
			entity.setXhsWarehouseOutputLogisticsList(
					xhsWarehouseOutputLogisticsDao.findList(xhsWarehouseOutputLogistics));
		}
		return entity;
	}

	/**
	 * 查询分页数据
	 * 
	 * @param xhsWarehouseOutput 查询条件
	 * @param                    xhsWarehouseOutput.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsWarehouseOutput> findPage(XhsWarehouseOutput xhsWarehouseOutput) {
		return super.findPage(xhsWarehouseOutput);
	}

	@Transactional(readOnly = false)
	public void saveLogistics(XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO) {
		XhsWarehouseOutputReceiver receiver = initXhsWarehouseOutputReceiver(xhsWarehouseOutputLogisticsBO);
		if (receiver != null) {
			receiver.preUpdate();
			xhsWarehouseOutputReceiverDao.update(receiver);
		}

		XhsWarehouseOutputSender sender = initXhsWarehouseOutputSender(xhsWarehouseOutputLogisticsBO);
		if (sender != null) {
			sender.preUpdate();
			xhsWarehouseOutputSenderDao.update(sender);
		}

		XhsWarehouseOutputLogistics logistics = initXhsWarehouseOutputLogistics(xhsWarehouseOutputLogisticsBO);
		if (logistics != null) {
			logistics.preUpdate();
			xhsWarehouseOutputLogisticsDao.update(logistics);
		}
	}

	private XhsWarehouseOutputReceiver initXhsWarehouseOutputReceiver(
			XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO) {
		if (StringHelper.isNullOrEmptyString(xhsWarehouseOutputLogisticsBO.getReceiverId())) {
			return null;
		}

		XhsWarehouseOutputReceiver receiver = new XhsWarehouseOutputReceiver();
		receiver.setOutputId(new XhsWarehouseOutput(xhsWarehouseOutputLogisticsBO.getOutputId()));
		receiver.setId(xhsWarehouseOutputLogisticsBO.getReceiverId());
		receiver.setReceiverName(xhsWarehouseOutputLogisticsBO.getReceiverName());
		receiver.setReceiverAddr(xhsWarehouseOutputLogisticsBO.getReceiverAddr());
		receiver.setReceiverPhone(xhsWarehouseOutputLogisticsBO.getReceiverPhone());

		return receiver;
	}

	private XhsWarehouseOutputSender initXhsWarehouseOutputSender(
			XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO) {
		if (StringHelper.isNullOrEmptyString(xhsWarehouseOutputLogisticsBO.getSenderId())) {
			return null;
		}

		XhsWarehouseOutputSender sender = new XhsWarehouseOutputSender();
		sender.setOutputId(new XhsWarehouseOutput(xhsWarehouseOutputLogisticsBO.getOutputId()));
		sender.setId(xhsWarehouseOutputLogisticsBO.getSenderId());
		sender.setSenderName(xhsWarehouseOutputLogisticsBO.getSenderName());
		sender.setSenderAddr(xhsWarehouseOutputLogisticsBO.getSenderAddr());
		sender.setSenderPhone(xhsWarehouseOutputLogisticsBO.getSenderPhone());

		return sender;
	}

	private XhsWarehouseOutputLogistics initXhsWarehouseOutputLogistics(
			XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO) {
		if (StringHelper.isNullOrEmptyString(xhsWarehouseOutputLogisticsBO.getLogisticsId())) {
			return null;
		}

		XhsWarehouseOutputLogistics logistics = new XhsWarehouseOutputLogistics();
		logistics.setOutputId(new XhsWarehouseOutput(xhsWarehouseOutputLogisticsBO.getOutputId()));
		logistics.setId(xhsWarehouseOutputLogisticsBO.getLogisticsId());
		logistics.setMailNumber(xhsWarehouseOutputLogisticsBO.getMailNumber());
		logistics.setMailType(xhsWarehouseOutputLogisticsBO.getMailType());
		logistics.setRemarks(xhsWarehouseOutputLogisticsBO.getLogisticsRemark());

		return logistics;
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param xhsWarehouseOutput
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(XhsWarehouseOutput xhsWarehouseOutput) {
		super.save(xhsWarehouseOutput);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(xhsWarehouseOutput.getId(), "xhsWarehouseOutput_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(xhsWarehouseOutput.getId(), "xhsWarehouseOutput_file");
		// 保存 XhsWarehouseOutput子表
		for (XhsWarehouseOutputReceiver xhsWarehouseOutputReceiver : xhsWarehouseOutput
				.getXhsWarehouseOutputReceiverList()) {
			if (!XhsWarehouseOutputReceiver.STATUS_DELETE.equals(xhsWarehouseOutputReceiver.getStatus())) {
				xhsWarehouseOutputReceiver.setOutputId(xhsWarehouseOutput);
				if (xhsWarehouseOutputReceiver.getIsNewRecord()) {
					xhsWarehouseOutputReceiver.preInsert();
					xhsWarehouseOutputReceiverDao.insert(xhsWarehouseOutputReceiver);
				} else {
					xhsWarehouseOutputReceiver.preUpdate();
					xhsWarehouseOutputReceiverDao.update(xhsWarehouseOutputReceiver);
				}
			} else {
				xhsWarehouseOutputReceiverDao.delete(xhsWarehouseOutputReceiver);
			}
		}
		// 保存 XhsWarehouseOutput子表
		for (XhsWarehouseOutputSender xhsWarehouseOutputSender : xhsWarehouseOutput.getXhsWarehouseOutputSenderList()) {
			if (!XhsWarehouseOutputSender.STATUS_DELETE.equals(xhsWarehouseOutputSender.getStatus())) {
				xhsWarehouseOutputSender.setOutputId(xhsWarehouseOutput);
				if (xhsWarehouseOutputSender.getIsNewRecord()) {
					xhsWarehouseOutputSender.preInsert();
					xhsWarehouseOutputSenderDao.insert(xhsWarehouseOutputSender);
				} else {
					xhsWarehouseOutputSender.preUpdate();
					xhsWarehouseOutputSenderDao.update(xhsWarehouseOutputSender);
				}
			} else {
				xhsWarehouseOutputSenderDao.delete(xhsWarehouseOutputSender);
			}
		}
		// 保存 XhsWarehouseOutput子表
		for (XhsWarehouseOutputLogistics xhsWarehouseOutputLogistics : xhsWarehouseOutput
				.getXhsWarehouseOutputLogisticsList()) {
			if (!XhsWarehouseOutputLogistics.STATUS_DELETE.equals(xhsWarehouseOutputLogistics.getStatus())) {
				xhsWarehouseOutputLogistics.setOutputId(xhsWarehouseOutput);
				if (xhsWarehouseOutputLogistics.getIsNewRecord()) {
					xhsWarehouseOutputLogistics.preInsert();
					xhsWarehouseOutputLogisticsDao.insert(xhsWarehouseOutputLogistics);
				} else {
					xhsWarehouseOutputLogistics.preUpdate();
					xhsWarehouseOutputLogisticsDao.update(xhsWarehouseOutputLogistics);
				}
			} else {
				xhsWarehouseOutputLogisticsDao.delete(xhsWarehouseOutputLogistics);
			}
		}
	}

	/**
	 * 更新状态
	 * 
	 * @param xhsWarehouseOutput
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStatus(XhsWarehouseOutput xhsWarehouseOutput) {
		super.updateStatus(xhsWarehouseOutput);
	}

	/**
	 * 删除数据
	 * 
	 * @param xhsWarehouseOutput
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(XhsWarehouseOutput xhsWarehouseOutput) {
		super.delete(xhsWarehouseOutput);
		XhsWarehouseOutputReceiver xhsWarehouseOutputReceiver = new XhsWarehouseOutputReceiver();
		xhsWarehouseOutputReceiver.setOutputId(xhsWarehouseOutput);
		xhsWarehouseOutputReceiverDao.delete(xhsWarehouseOutputReceiver);
		XhsWarehouseOutputSender xhsWarehouseOutputSender = new XhsWarehouseOutputSender();
		xhsWarehouseOutputSender.setOutputId(xhsWarehouseOutput);
		xhsWarehouseOutputSenderDao.delete(xhsWarehouseOutputSender);
		XhsWarehouseOutputLogistics xhsWarehouseOutputLogistics = new XhsWarehouseOutputLogistics();
		xhsWarehouseOutputLogistics.setOutputId(xhsWarehouseOutput);
		xhsWarehouseOutputLogisticsDao.delete(xhsWarehouseOutputLogistics);
	}

	public LogisticsInfoResultBO queryLogisticsInfo(String mailType, String mailNumber) {
		LogisticsInfoResultBO result = new LogisticsInfoResultBO();

		try {
			HttpGet method = new HttpGet(String.format(URL_QUERY_LOGISTICS, mailType, mailNumber));
			// 发起请求
			CloseableHttpResponse response = httpclient.execute(method);

			int httpCode = response.getStatusLine().getStatusCode();
			result.setResult(FastJsonUtil.parseToClass(
					CoderUtils.unicodeToString(IOUtil.readStreamAsString(response.getEntity().getContent(), "UTF-8")),
					QueryLogisticsResponseBO.class));
			result.setCode(httpCode);

			// 判断状态码是否为200
			if (httpCode != 200) {
				result.setMsg(text("查询失败，返回HTTP CODE:" + httpCode));
			}
		} catch (Exception e) {
			result.setCode(0);
			result.setMsg(e.getMessage());

			e.printStackTrace();
		}

		return result;
	}
}