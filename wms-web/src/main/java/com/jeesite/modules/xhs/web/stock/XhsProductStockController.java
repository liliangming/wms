/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.stock;

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
import com.jeesite.modules.xhs.entity.stock.XhsProductStock;
import com.jeesite.modules.xhs.service.stock.XhsProductStockService;

/**
 * 产品库存信息Controller
 * @author liliangming
 * @version 2018-12-23
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/stock/xhsProductStock")
public class XhsProductStockController extends BaseController {

	@Autowired
	private XhsProductStockService xhsProductStockService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsProductStock get(String id, boolean isNewRecord) {
		return xhsProductStockService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:stock:xhsProductStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(XhsProductStock xhsProductStock, Model model) {
		model.addAttribute("xhsProductStock", xhsProductStock);
		return "modules/xhs/stock/xhsProductStockList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:stock:xhsProductStock:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsProductStock> listData(XhsProductStock xhsProductStock, HttpServletRequest request, HttpServletResponse response) {
		xhsProductStock.setPage(new Page<>(request, response));
		Page<XhsProductStock> page = xhsProductStockService.findPage(xhsProductStock); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:stock:xhsProductStock:view")
	@RequestMapping(value = "form")
	public String form(XhsProductStock xhsProductStock, Model model) {
		model.addAttribute("xhsProductStock", xhsProductStock);
		return "modules/xhs/stock/xhsProductStockForm";
	}

	/**
	 * 保存产品库存信息
	 */
	@RequiresPermissions("xhs:stock:xhsProductStock:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsProductStock xhsProductStock) {
		xhsProductStockService.save(xhsProductStock);
		return renderResult(Global.TRUE, text("保存产品库存信息成功！"));
	}
	
	/**
	 * 删除产品库存信息
	 */
	@RequiresPermissions("xhs:stock:xhsProductStock:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsProductStock xhsProductStock) {
		xhsProductStockService.delete(xhsProductStock);
		return renderResult(Global.TRUE, text("删除产品库存信息成功！"));
	}
	
}