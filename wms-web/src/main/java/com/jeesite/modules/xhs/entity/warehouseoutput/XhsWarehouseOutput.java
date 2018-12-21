/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouseoutput;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerInfo;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

/**
 * 出库信息Entity
 * 
 * @author liliangming
 * @version 2018-12-20
 */
@Table(name = "xhs_warehouse_output", alias = "a", columns = {
		@Column(name = "id", attrName = "id", label = "出库单号", isPK = true),
		@Column(name = "product_id", attrName = "product.id", label = "产品"),
		@Column(name = "output_price", attrName = "outputPrice", label = "出货单价"),
		@Column(name = "output_per", attrName = "outputPer", label = "出货单价单位数量"),
		@Column(name = "output_quantity", attrName = "outputQuantity", label = "出库数量"),
		@Column(name = "warehouse_code", attrName = "warehouse.treeCode", label = "出库仓库编码"),
		@Column(name = "customer_id", attrName = "customer.id", label = "出货客户"),
		@Column(includeEntity = DataEntity.class),
		@Column(name = "output_status", attrName = "outputStatus", label = "出货状态"),
		@Column(name = "currency", attrName = "currency", label = "币种"), }, joinTable = {
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsProductInfo.class, attrName = "product", alias = "product", on = "product.id = a.product_id", columns = {
						@Column(includeEntity = XhsProductInfo.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsWarehouseTree.class, attrName = "warehouse", alias = "warehouse", on = "warehouse.tree_code = a.warehouse_code", columns = {
						@Column(includeEntity = XhsWarehouseTree.class) }),
				@JoinTable(type = Type.LEFT_JOIN, entity = XhsCustomerInfo.class, attrName = "customer", alias = "customer", on = "customer.id = a.customer_id", columns = {
						@Column(includeEntity = XhsCustomerInfo.class) }), }, orderBy = "a.update_date DESC")
public class XhsWarehouseOutput extends DataEntity<XhsWarehouseOutput> {

	private static final long serialVersionUID = 1L;
	private XhsProductInfo product; // 产品
	private Double outputPrice; // 出货单价
	private Long outputPer; // 出货单价单位数量
	private Long outputQuantity; // 出库数量
	private XhsWarehouseTree warehouse; // 出库仓库
	private XhsCustomerInfo customer; // 出货客户
	private String outputStatus; // 出货状态
	private String currency; // 币种
	private List<XhsWarehouseOutputReceiver> xhsWarehouseOutputReceiverList = ListUtils.newArrayList(); // 子表列表
	private List<XhsWarehouseOutputSender> xhsWarehouseOutputSenderList = ListUtils.newArrayList(); // 子表列表
	private List<XhsWarehouseOutputLogistics> xhsWarehouseOutputLogisticsList = ListUtils.newArrayList(); // 子表列表

	private String outputPriceDesc;
//	private Boolean isCanPrintExpress; // 资料是否完善，可以打印快递单
//	private Boolean isRecordLogistics; // 是否已经录入物流信息
//
//	private String receiverName; // 收件人姓名
//	private String receiverAddr; // 收件人地址
//	private String receiverPhone; // 收件人电话
//	private String senderName; // 寄件人姓名
//	private String senderAddr; // 寄件人地址
//	private String senderPhone; // 寄件人电话
//	private String logisticsRemark; // 物流备注

	public XhsWarehouseOutput() {
		this(null);
	}

	public XhsWarehouseOutput(String id) {
		super(id);
	}

	public Double getOutputPrice() {
		return outputPrice;
	}

	public void setOutputPrice(Double outputPrice) {
		this.outputPrice = outputPrice;
	}

	public Long getOutputPer() {
		return outputPer;
	}

	public void setOutputPer(Long outputPer) {
		this.outputPer = outputPer;
	}

	@NotNull(message = "出库数量不能为空")
	public Long getOutputQuantity() {
		return outputQuantity;
	}

	public void setOutputQuantity(Long outputQuantity) {
		this.outputQuantity = outputQuantity;
	}

	@Length(min = 0, max = 50, message = "出货状态长度不能超过 50 个字符")
	public String getOutputStatus() {
		return outputStatus;
	}

	public void setOutputStatus(String outputStatus) {
		this.outputStatus = outputStatus;
	}

	@Length(min = 0, max = 10, message = "币种长度不能超过 10 个字符")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<XhsWarehouseOutputReceiver> getXhsWarehouseOutputReceiverList() {
		return xhsWarehouseOutputReceiverList;
	}

	public void setXhsWarehouseOutputReceiverList(List<XhsWarehouseOutputReceiver> xhsWarehouseOutputReceiverList) {
		this.xhsWarehouseOutputReceiverList = xhsWarehouseOutputReceiverList;
	}

	public List<XhsWarehouseOutputSender> getXhsWarehouseOutputSenderList() {
		return xhsWarehouseOutputSenderList;
	}

	public void setXhsWarehouseOutputSenderList(List<XhsWarehouseOutputSender> xhsWarehouseOutputSenderList) {
		this.xhsWarehouseOutputSenderList = xhsWarehouseOutputSenderList;
	}

	public List<XhsWarehouseOutputLogistics> getXhsWarehouseOutputLogisticsList() {
		return xhsWarehouseOutputLogisticsList;
	}

	public void setXhsWarehouseOutputLogisticsList(List<XhsWarehouseOutputLogistics> xhsWarehouseOutputLogisticsList) {
		this.xhsWarehouseOutputLogisticsList = xhsWarehouseOutputLogisticsList;
	}

	public XhsProductInfo getProduct() {
		return product;
	}

	public void setProduct(XhsProductInfo product) {
		this.product = product;
	}

	public XhsWarehouseTree getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(XhsWarehouseTree warehouse) {
		this.warehouse = warehouse;
	}

	public XhsCustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(XhsCustomerInfo customer) {
		this.customer = customer;
	}

	public String getOutputPriceDesc() {
		if (outputPriceDesc == null) {
			outputPriceDesc = buildOutputPriceDesc();
		}
		return outputPriceDesc;
	}

	private String buildOutputPriceDesc() {
		if (this.outputPrice == null) {
			return "";
		}

		return String.format("%s元/%s个", this.outputPrice, this.outputPer == null ? 1 : this.outputPer);
	}

//	public boolean getIsCanPrintExpress() {
//		if (isCanPrintExpress == null) {
//			isCanPrintExpress = isCanPrintExpress();
//		}
//
//		return isCanPrintExpress;
//	}
//
//	private boolean isCanPrintExpress() {
//		return hasReceiver() && hasSender();
//	}
//
//	private boolean hasReceiver() {
//		if (xhsWarehouseOutputReceiverList == null || xhsWarehouseOutputReceiverList.isEmpty()) {
//			return false;
//		}
//
//		XhsWarehouseOutputReceiver receiver = xhsWarehouseOutputReceiverList.get(0);
//		if (receiver == null) {
//			return false;
//		}
//
//		return !StringHelper.isNullOrEmptyString(receiver.getReceiverName())
//				&& !StringHelper.isNullOrEmptyString(receiver.getReceiverAddr())
//				&& !StringHelper.isNullOrEmptyString(receiver.getReceiverPhone());
//	}
//
//	private boolean hasSender() {
//		if (xhsWarehouseOutputSenderList == null || xhsWarehouseOutputSenderList.isEmpty()) {
//			return false;
//		}
//
//		XhsWarehouseOutputSender sender = xhsWarehouseOutputSenderList.get(0);
//		if (sender == null) {
//			return false;
//		}
//
//		return !StringHelper.isNullOrEmptyString(sender.getSenderName())
//				&& !StringHelper.isNullOrEmptyString(sender.getSenderAddr())
//				&& !StringHelper.isNullOrEmptyString(sender.getSenderPhone());
//	}
//
//	public boolean getIsRecordLogistics() {
//		if (isRecordLogistics == null) {
//			isRecordLogistics = isRecordLogistics();
//		}
//
//		return isRecordLogistics;
//	}
//
//	private boolean isRecordLogistics() {
//		if (xhsWarehouseOutputLogisticsList == null || xhsWarehouseOutputLogisticsList.isEmpty()) {
//			return false;
//		}
//
//		XhsWarehouseOutputLogistics logistics = xhsWarehouseOutputLogisticsList.get(0);
//		if (logistics == null) {
//			return false;
//		}
//
//		return !StringHelper.isNullOrEmptyString(logistics.getMailType())
//				&& !StringHelper.isNullOrEmptyString(logistics.getMailNumber());
//	}
//
//	public String getReceiverName() {
//		if (receiverName == null) {
//			initLogisticsInfo();
//		}
//
//		return receiverName;
//	}
//
//	public String getReceiverAddr() {
//		if (receiverAddr == null) {
//			initLogisticsInfo();
//		}
//		return receiverAddr;
//	}
//
//	public String getReceiverPhone() {
//		if (receiverPhone == null) {
//			initLogisticsInfo();
//		}
//		return receiverPhone;
//	}
//
//	public String getSenderName() {
//		if (senderName == null) {
//			initLogisticsInfo();
//		}
//		return senderName;
//	}
//
//	public String getSenderAddr() {
//		if (senderAddr == null) {
//			initLogisticsInfo();
//		}
//		return senderAddr;
//	}
//
//	public String getSenderPhone() {
//		if (senderPhone == null) {
//			initLogisticsInfo();
//		}
//		return senderPhone;
//	}
//
//	public String getLogisticsRemark() {
//		if (logisticsRemark == null) {
//			initLogisticsInfo();
//		}
//
//		return logisticsRemark;
//	}
//
//	private void initLogisticsInfo() {
//		receiverName = "";
//		receiverAddr = "";
//		receiverPhone = "";
//		senderName = "";
//		senderAddr = "";
//		senderPhone = "";
//		logisticsRemark = "";
//
//		if (xhsWarehouseOutputReceiverList != null && !xhsWarehouseOutputReceiverList.isEmpty()) {
//			XhsWarehouseOutputReceiver receiver = xhsWarehouseOutputReceiverList.get(0);
//			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverName())) {
//				receiverName = receiver.getReceiverName();
//			}
//			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverAddr())) {
//				receiverAddr = receiver.getReceiverAddr();
//			}
//			if (!StringHelper.isNullOrEmptyString(receiver.getReceiverPhone())) {
//				receiverPhone = receiver.getReceiverPhone();
//			}
//		}
//
//		if (xhsWarehouseOutputSenderList != null && !xhsWarehouseOutputSenderList.isEmpty()) {
//			XhsWarehouseOutputSender sender = xhsWarehouseOutputSenderList.get(0);
//			if (!StringHelper.isNullOrEmptyString(sender.getSenderName())) {
//				senderName = sender.getSenderName();
//			}
//			if (!StringHelper.isNullOrEmptyString(sender.getSenderAddr())) {
//				senderAddr = sender.getSenderAddr();
//			}
//			if (!StringHelper.isNullOrEmptyString(sender.getSenderPhone())) {
//				senderPhone = sender.getSenderPhone();
//			}
//		}
//
//		if (xhsWarehouseOutputLogisticsList != null && !xhsWarehouseOutputLogisticsList.isEmpty()) {
//			logisticsRemark = xhsWarehouseOutputLogisticsList.get(0).getRemarks();
//		}
//	}
}