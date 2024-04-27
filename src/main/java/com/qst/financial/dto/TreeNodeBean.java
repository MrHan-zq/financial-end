package com.qst.financial.dto;

import java.util.List;
import java.util.Map;

/**
 * @author qst
 * 
 * @类说明：树节点实体对象
 */
public class TreeNodeBean {

	/**
	 * 节点的属性
	 */
	private Map<String, String> attributes;

	/**
	 * 树节点数据
	 */
	private String data;

	/**
	 * 节点的状态（是否展开）
	 */
	private String state;

	/**
	 * 子节点仍然为MenuNode，无限递归。
	 */
	private List<TreeNodeBean> children;

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeBean> children) {
		this.children = children;
	}

}
