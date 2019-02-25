package cn.jsonXxxx.jyTest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "menu_id", type = IdType.AUTO)
	@JsonIgnore
	private Long menuId;
	/**
	 * 父菜单ID，一级菜单为0
	 */
	@JsonIgnore
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String title;
	/**
	 * 菜单URL
	 */
	private String href;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@JsonIgnore
	private String perms;
	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	@JsonIgnore
	private Integer type;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	@JsonIgnore
	private Integer orderNum;

	@TableField(exist = false)
	private List<Menu> children = new ArrayList<Menu>();

	@TableField(exist = false)
	private Boolean spread = false;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getSpread() {
		return spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", parentId=" + parentId + ", title=" + title + ", href=" + href + ", perms="
				+ perms + ", type=" + type + ", icon=" + icon + ", orderNum=" + orderNum + ", children=" + children
				+ ", spread=" + spread + "]";
	}

}
