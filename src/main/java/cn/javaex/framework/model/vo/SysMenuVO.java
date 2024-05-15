package cn.javaex.framework.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 * 
 * @author 陈霓清
 */
public class SysMenuVO {
	private String id;                      // 主键
	private String parentId;                // 父级节点id
	private String name;                    // 菜单名称
	private String url;                     // 菜单链接
	private Integer sort;                   // 显示顺序
	private String icon;                    // 图标
	private String permCode;                // 按钮权限标识
	private String type;                    // 菜单类型
	private Integer isHidden;               // 是否隐藏
	private Integer isSystem;               // 是否系统内置菜单
	
	private boolean checked;	// 是否选中（权限配置时使用）
	private boolean open;		// 是否展开（权限配置时使用）
	private List<SysMenuVO> children = new ArrayList<SysMenuVO>();
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<SysMenuVO> getChildren() {
		return children;
	}
	public void setChildren(List<SysMenuVO> children) {
		this.children = children;
	}
	
	/**
	 * 得到主键
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 得到父级节点id
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置父级节点id
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 得到菜单名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置菜单名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 得到菜单链接
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置菜单链接
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 得到图标
	 * @return
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 得到权限标识
	 * @return
	 */
	public String getPermCode() {
		return permCode;
	}
	/**
	 * 设置权限标识
	 * @param permCode
	 */
	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}

	/**
	 * 得到菜单类型
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置菜单类型
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 得到排序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置排序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 得到是否隐藏
	 * @return
	 */
	public Integer getIsHidden() {
		return isHidden;
	}
	/**
	 * 设置是否隐藏
	 * @param isHidden
	 */
	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}
	/**
	 * 得到是否系统内置菜单
	 * @return
	 */
	public Integer getIsSystem() {
		return isSystem;
	}
	/**
	 * 设置是否系统内置菜单
	 * @param isSystem
	 */
	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}
	
}
