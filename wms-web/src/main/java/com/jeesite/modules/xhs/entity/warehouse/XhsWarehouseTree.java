/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.entity.warehouse;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 仓库信息Entity
 * @author liliangming
 * @version 2018-12-19
 */
@Table(name="xhs_warehouse_tree", alias="a", columns={
		@Column(name="tree_code", attrName="treeCode", label="节点编码", queryType=QueryType.LIKE, isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="tree_name", attrName="treeName", label="节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="address", attrName="address", label="仓库地址"),
		@Column(name="wh_type", attrName="whType", label="仓库类型"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.tree_sorts, a.tree_code"
)
public class XhsWarehouseTree extends TreeEntity<XhsWarehouseTree> {
	
	private static final long serialVersionUID = 1L;
	private String treeCode;		// 节点编码
	private String treeName;		// 节点名称
	private String address;		// 仓库地址
	private String whType;		// 仓库类型
	
	public XhsWarehouseTree() {
		this(null);
	}

	public XhsWarehouseTree(String id){
		super(id);
	}
	
	@Override
	public XhsWarehouseTree getParent() {
		return parent;
	}

	@Override
	public void setParent(XhsWarehouseTree parent) {
		this.parent = parent;
	}
	
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	
	@NotBlank(message="节点名称不能为空")
	@Length(min=0, max=200, message="节点名称长度不能超过 200 个字符")
	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
	@Length(min=0, max=200, message="仓库地址长度不能超过 200 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1, message="仓库类型长度不能超过 1 个字符")
	public String getWhType() {
		return whType;
	}

	public void setWhType(String whType) {
		this.whType = whType;
	}
	
}