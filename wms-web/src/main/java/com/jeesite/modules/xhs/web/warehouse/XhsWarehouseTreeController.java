/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.warehouse;

import java.util.List;
import java.util.Map;

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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;
import com.jeesite.modules.xhs.service.warehouse.XhsWarehouseTreeService;

/**
 * 仓库信息Controller
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/warehouse/xhsWarehouseTree")
public class XhsWarehouseTreeController extends BaseController {

	@Autowired
	private XhsWarehouseTreeService xhsWarehouseTreeService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsWarehouseTree get(String treeCode, boolean isNewRecord) {
		return xhsWarehouseTreeService.get(treeCode, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:view")
	@RequestMapping(value = { "list", "" })
	public String list(XhsWarehouseTree xhsWarehouseTree, Model model) {
		model.addAttribute("xhsWarehouseTree", xhsWarehouseTree);
		return "modules/xhs/warehouse/xhsWarehouseTreeList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<XhsWarehouseTree> listData(XhsWarehouseTree xhsWarehouseTree) {
		if (StringUtils.isBlank(xhsWarehouseTree.getParentCode())) {
			xhsWarehouseTree.setParentCode(XhsWarehouseTree.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(xhsWarehouseTree.getTreeName())) {
			xhsWarehouseTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(xhsWarehouseTree.getRemarks())) {
			xhsWarehouseTree.setParentCode(null);
		}
		List<XhsWarehouseTree> list = xhsWarehouseTreeService.findList(xhsWarehouseTree);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:view")
	@RequestMapping(value = "form")
	public String form(XhsWarehouseTree xhsWarehouseTree, Model model) {
		// 创建并初始化下一个节点信息
		xhsWarehouseTree = createNextNode(xhsWarehouseTree);
		model.addAttribute("xhsWarehouseTree", xhsWarehouseTree);
		return xhsWarehouseTree.getParentCode() == null ? "modules/xhs/warehouse/xhsWarehouseForm"
				: "modules/xhs/warehouse/xhsWarehousePositionForm";
	}

	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public XhsWarehouseTree createNextNode(XhsWarehouseTree xhsWarehouseTree) {
		if (StringUtils.isNotBlank(xhsWarehouseTree.getParentCode())) {
			xhsWarehouseTree.setParent(xhsWarehouseTreeService.get(xhsWarehouseTree.getParentCode()));
		}
		if (xhsWarehouseTree.getIsNewRecord()) {
			XhsWarehouseTree where = new XhsWarehouseTree();
			where.setParentCode(xhsWarehouseTree.getParentCode());
			XhsWarehouseTree last = xhsWarehouseTreeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null) {
				xhsWarehouseTree.setTreeSort(last.getTreeSort() + 30);
				xhsWarehouseTree.setTreeCode(IdGen.nextCode(last.getTreeCode()));
			} else if (xhsWarehouseTree.getParent() != null) {
				xhsWarehouseTree.setTreeCode(xhsWarehouseTree.getParent().getTreeCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (xhsWarehouseTree.getTreeSort() == null) {
			xhsWarehouseTree.setTreeSort(XhsWarehouseTree.DEFAULT_TREE_SORT);
		}
		return xhsWarehouseTree;
	}

	/**
	 * 保存仓库信息
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsWarehouseTree xhsWarehouseTree) {
		xhsWarehouseTreeService.save(xhsWarehouseTree);
		return renderResult(Global.TRUE, text(xhsWarehouseTree.getIsRoot() ? "保存仓库信息成功！" : "保存分区信息成功！"));
	}

	/**
	 * 删除仓库信息
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsWarehouseTree xhsWarehouseTree) {
		xhsWarehouseTreeService.delete(xhsWarehouseTree);
		return renderResult(Global.TRUE, text(xhsWarehouseTree.getIsRoot() ? "删除仓库信息成功！" : "删除分区信息成功！"));
	}

	/**
	 * 获取树结构数据
	 * 
	 * @param excludeCode 排除的Code
	 * @param isShowCode  是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<XhsWarehouseTree> list = xhsWarehouseTreeService.findList(new XhsWarehouseTree());
		for (int i = 0; i < list.size(); i++) {
			XhsWarehouseTree e = list.get(i);
			// 过滤非正常的数据
			if (!XhsWarehouseTree.STATUS_NORMAL.equals(e.getStatus())) {
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)) {
				if (e.getId().equals(excludeCode)) {
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")) {
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getTreeCode(), e.getTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("xhs:warehouse:xhsWarehouseTree:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(XhsWarehouseTree xhsWarehouseTree) {
		if (!UserUtils.getUser().isAdmin()) {
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		xhsWarehouseTreeService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}

}