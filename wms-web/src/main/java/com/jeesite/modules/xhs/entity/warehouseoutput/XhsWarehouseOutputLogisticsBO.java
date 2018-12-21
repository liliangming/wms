/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouseoutput;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.util.StringHelper;

import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutput;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputLogistics;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputReceiver;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputSender;

/**
 * 出货单物流信息BO
 * 
 * @author liliangming
 * @version 2018-12-20
 */
public class XhsWarehouseOutputLogisticsBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1857401989603682602L;

	private String outputId; // 出货单号

	private Boolean isCanPrintExpress; // 资料是否完善，可以打印快递单
	private Boolean isRecordLogistics; // 是否已经录入物流信息

	private String mailType; // 快递类型
	private String mailNumber; // 快递单号
	private String logisticsId; // 物流id
	private String logisticsRemark; // 物流备注
	private LogisticsInfoResultBO logisticsInfo; // 查询到的物流信息

	private String receiverId; // 收件人id
	private String receiverName; // 收件人姓名
	private String receiverAddr; // 收件人地址
	private String receiverPhone; // 收件人电话
	private String senderId; // 寄件人id
	private String senderName; // 寄件人姓名
	private String senderAddr; // 寄件人地址
	private String senderPhone; // 寄件人电话

	public XhsWarehouseOutputLogisticsBO() {
	}

	public XhsWarehouseOutputLogisticsBO(String outputId) {
		this.outputId = outputId;
	}

	public XhsWarehouseOutputLogisticsBO(XhsWarehouseOutput xhsWarehouseOutput) {
		this.outputId = xhsWarehouseOutput.getId();

		if (xhsWarehouseOutput.getXhsWarehouseOutputReceiverList() != null
				&& !xhsWarehouseOutput.getXhsWarehouseOutputReceiverList().isEmpty()) {
			XhsWarehouseOutputReceiver receiver = xhsWarehouseOutput.getXhsWarehouseOutputReceiverList().get(0);
			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverName())) {
				receiverName = receiver.getReceiverName();
			}
			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverAddr())) {
				receiverAddr = receiver.getReceiverAddr();
			}
			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverPhone())) {
				receiverPhone = receiver.getReceiverPhone();
			}
			if (!StringHelper.isNullOrEmptyString(receiver.getId())) {
				receiverId = receiver.getId();
			}
		}

		if (xhsWarehouseOutput.getXhsWarehouseOutputSenderList() != null
				&& !xhsWarehouseOutput.getXhsWarehouseOutputSenderList().isEmpty()) {
			XhsWarehouseOutputSender sender = xhsWarehouseOutput.getXhsWarehouseOutputSenderList().get(0);
			if (!StringHelper.isNullOrEmptyString(sender.getSenderName())) {
				senderName = sender.getSenderName();
			}
			if (!StringHelper.isNullOrEmptyString(sender.getSenderAddr())) {
				senderAddr = sender.getSenderAddr();
			}
			if (!StringHelper.isNullOrEmptyString(sender.getSenderPhone())) {
				senderPhone = sender.getSenderPhone();
			}
			if (!StringHelper.isNullOrEmptyString(sender.getId())) {
				senderId = sender.getId();
			}
		}

		if (xhsWarehouseOutput.getXhsWarehouseOutputLogisticsList() != null
				&& !xhsWarehouseOutput.getXhsWarehouseOutputLogisticsList().isEmpty()) {
			XhsWarehouseOutputLogistics logistics = xhsWarehouseOutput.getXhsWarehouseOutputLogisticsList().get(0);
			logisticsId = logistics.getId();
			mailType = logistics.getMailType();
			mailNumber = logistics.getMailNumber();
			logisticsRemark = logistics.getRemarks();
		}
	}

	@Length(min = 0, max = 50, message = "出货单号长度不能超过 50 个字符")
	public String getOutputId() {
		return outputId;
	}

	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}

	@Length(min = 0, max = 20, message = "快递类型长度不能超过 20 个字符")
	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	@Length(min = 0, max = 50, message = "快递单号长度不能超过 50 个字符")
	public String getMailNumber() {
		return mailNumber;
	}

	public void setMailNumber(String mailNumber) {
		this.mailNumber = mailNumber;
	}

	public Boolean getIsCanPrintExpress() {
		if (isCanPrintExpress == null) {
			isCanPrintExpress = hasReceiver() && hasSender();
		}

		return isCanPrintExpress;
	}

	private boolean hasReceiver() {
		return !StringHelper.isNullOrEmptyString(receiverName) && !StringHelper.isNullOrEmptyString(receiverAddr)
				&& !StringHelper.isNullOrEmptyString(receiverPhone);
	}

	private boolean hasSender() {
		return !StringHelper.isNullOrEmptyString(senderName) && !StringHelper.isNullOrEmptyString(senderAddr)
				&& !StringHelper.isNullOrEmptyString(senderPhone);
	}

	public Boolean getIsRecordLogistics() {
		if (isRecordLogistics == null) {
			isRecordLogistics = !StringHelper.isNullOrEmptyString(mailType)
					&& !StringHelper.isNullOrEmptyString(mailNumber);
		}

		return isRecordLogistics;
	}

	public String getLogisticsRemark() {
		return logisticsRemark;
	}

	public void setLogisticsRemark(String logisticsRemark) {
		this.logisticsRemark = logisticsRemark;
	}

	@Length(min = 0, max = 100, message = "收件人姓名长度不能超过 100 个字符")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Length(min = 0, max = 200, message = "收件人地址长度不能超过 200 个字符")
	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	@Length(min = 0, max = 20, message = "收件人电话长度不能超过 20 个字符")
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	@Length(min = 0, max = 100, message = "寄件人姓名长度不能超过 100 个字符")
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Length(min = 0, max = 200, message = "寄件人地址长度不能超过 200 个字符")
	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}

	@Length(min = 0, max = 20, message = "寄件人电话长度不能超过 20 个字符")
	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public LogisticsInfoResultBO getLogisticsInfo() {
		return logisticsInfo;
	}

	public void setLogisticsInfo(LogisticsInfoResultBO logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}
}