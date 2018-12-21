/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.warehouseoutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutput;
import com.jeesite.modules.xhs.entity.warehouseoutput.XhsWarehouseOutputLogisticsBO;
import com.jeesite.modules.xhs.service.warehouseoutput.XhsWarehouseOutputService;

/**
 * 出库信息Controller
 * 
 * @author liliangming
 * @version 2018-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/warehouseoutput/xhsWarehouseOutput")
public class XhsWarehouseOutputController extends BaseController {

	@Autowired
	private XhsWarehouseOutputService xhsWarehouseOutputService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsWarehouseOutput get(String id, boolean isNewRecord) {
		return xhsWarehouseOutputService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = { "list", "" })
	public String list(XhsWarehouseOutput xhsWarehouseOutput, Model model) {
		model.addAttribute("xhsWarehouseOutput", xhsWarehouseOutput);
		return "modules/xhs/warehouseoutput/xhsWarehouseOutputList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsWarehouseOutput> listData(XhsWarehouseOutput xhsWarehouseOutput, HttpServletRequest request,
			HttpServletResponse response) {
		xhsWarehouseOutput.setPage(new Page<>(request, response));
		Page<XhsWarehouseOutput> page = xhsWarehouseOutputService.findPage(xhsWarehouseOutput);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = "form")
	public String form(XhsWarehouseOutput xhsWarehouseOutput, Model model) {
		model.addAttribute("xhsWarehouseOutput", xhsWarehouseOutput);
		return "modules/xhs/warehouseoutput/xhsWarehouseOutputForm";
	}

	/**
	 * 保存出库信息
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsWarehouseOutput xhsWarehouseOutput) {
		xhsWarehouseOutputService.save(xhsWarehouseOutput);
		return renderResult(Global.TRUE, text("保存出库信息成功！"));
	}

	/**
	 * 删除出库信息
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsWarehouseOutput xhsWarehouseOutput) {
		xhsWarehouseOutputService.delete(xhsWarehouseOutput);
		return renderResult(Global.TRUE, text("删除出库信息成功！"));
	}

	/**
	 * 快递单打印
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = "printLogistics")
	public String printLogistics(XhsWarehouseOutput xhsWarehouseOutput, Model model) {
		model.addAttribute("xhsWarehouseOutputLogisticsBO", new XhsWarehouseOutputLogisticsBO(xhsWarehouseOutput));
		return "modules/xhs/warehouseoutput/printLogistics";
	}

	/**
	 * 录入快递单
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = "recordLogistics")
	public String recordLogistics(XhsWarehouseOutput xhsWarehouseOutput, Model model) {
		model.addAttribute("xhsWarehouseOutputLogisticsBO", new XhsWarehouseOutputLogisticsBO(xhsWarehouseOutput));
		return "modules/xhs/warehouseoutput/recordLogistics";
	}

	/**
	 * 物流信息
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:view")
	@RequestMapping(value = "trackLogistics")
	public String trackLogistics(XhsWarehouseOutput xhsWarehouseOutput, Model model) {
		XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO = new XhsWarehouseOutputLogisticsBO(
				xhsWarehouseOutput);
		model.addAttribute("xhsWarehouseOutputLogisticsBO", xhsWarehouseOutputLogisticsBO);
		if (xhsWarehouseOutputLogisticsBO.getIsRecordLogistics()) {
			xhsWarehouseOutputLogisticsBO.setLogisticsInfo(xhsWarehouseOutputService.queryLogisticsInfo(
					xhsWarehouseOutputLogisticsBO.getMailType(), xhsWarehouseOutputLogisticsBO.getMailNumber()));
		}

		return "modules/xhs/warehouseoutput/trackLogistics";
	}

	/**
	 * 保存物流信息
	 */
	@RequiresPermissions("xhs:warehouseoutput:xhsWarehouseOutput:edit")
	@PostMapping(value = "saveLogistics")
	@ResponseBody
	public String saveLogistics(@Validated XhsWarehouseOutputLogisticsBO xhsWarehouseOutputLogisticsBO) {
		xhsWarehouseOutputService.saveLogistics(xhsWarehouseOutputLogisticsBO);
		return renderResult(Global.TRUE, text("保存物流信息成功！"));
	}
}