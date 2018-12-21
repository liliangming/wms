/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.warehouseinput;

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
import com.jeesite.modules.xhs.entity.warehouseinput.XhsWarehouseInput;
import com.jeesite.modules.xhs.service.warehouseinput.XhsWarehouseInputService;

/**
 * 入库登记Controller
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/warehouseinput/xhsWarehouseInput")
public class XhsWarehouseInputController extends BaseController {

	@Autowired
	private XhsWarehouseInputService xhsWarehouseInputService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsWarehouseInput get(String id, boolean isNewRecord) {
		return xhsWarehouseInputService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:warehouseinput:xhsWarehouseInput:view")
	@RequestMapping(value = { "list", "" })
	public String list(XhsWarehouseInput xhsWarehouseInput, Model model) {
		parseWarehouse(xhsWarehouseInput);

		model.addAttribute("xhsWarehouseInput", xhsWarehouseInput);
		return "modules/xhs/warehouseinput/xhsWarehouseInputList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:warehouseinput:xhsWarehouseInput:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsWarehouseInput> listData(XhsWarehouseInput xhsWarehouseInput, HttpServletRequest request,
			HttpServletResponse response) {
		// 解析仓库和分区
		parseWarehouse(xhsWarehouseInput);

		xhsWarehouseInput.setPage(new Page<>(request, response));
		Page<XhsWarehouseInput> page = xhsWarehouseInputService.findPage(xhsWarehouseInput);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:warehouseinput:xhsWarehouseInput:view")
	@RequestMapping(value = "form")
	public String form(XhsWarehouseInput xhsWarehouseInput, Model model) {
		parseWarehouse(xhsWarehouseInput);

		model.addAttribute("xhsWarehouseInput", xhsWarehouseInput);
		return "modules/xhs/warehouseinput/xhsWarehouseInputForm";
	}

	/**
	 * 保存入库登记
	 */
	@RequiresPermissions("xhs:warehouseinput:xhsWarehouseInput:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsWarehouseInput xhsWarehouseInput) {
		// 解析仓库和分区
		parseWarehouse(xhsWarehouseInput);

		xhsWarehouseInputService.save(xhsWarehouseInput);
		return renderResult(Global.TRUE, text("保存入库登记成功！"));
	}

	/**
	 * 删除入库登记
	 */
	@RequiresPermissions("xhs:warehouseinput:xhsWarehouseInput:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsWarehouseInput xhsWarehouseInput) {
		parseWarehouse(xhsWarehouseInput);

		xhsWarehouseInputService.delete(xhsWarehouseInput);
		return renderResult(Global.TRUE, text("删除入库登记成功！"));
	}

	/**
	 * 解析仓库和分区
	 * 
	 * @param xhsWarehouseInput
	 */
	private void parseWarehouse(XhsWarehouseInput xhsWarehouseInput) {
//		if (xhsWarehouseInput.getPosition() == null || xhsWarehouseInput.getPosition().getId() == null) {
//			return;
//		}
//
//		String[] spilts = xhsWarehouseInput.getPosition().getId().split(",");
//		if (spilts.length > 0) {
//			xhsWarehouseInput.setWarehouse(new XhsWarehouseInfo(spilts[0]));
//			xhsWarehouseInput.getPosition().setId(null);
//		}
//
//		if (spilts.length > 1) {
//			xhsWarehouseInput.getPosition().setId(spilts[1]);
//		}
	}

}