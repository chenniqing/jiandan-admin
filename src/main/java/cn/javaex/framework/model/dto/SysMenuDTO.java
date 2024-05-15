package cn.javaex.framework.model.dto;

/**
 * 菜单表
 * 
 * @author 陈霓清
 */
public class SysMenuDTO {
	private String parentId;                // 父级节点id
	private String name;                    // 菜单名称
	private String url;                     // 菜单链接
	private Integer sort;                   // 显示顺序
	private String icon;                    // 图标
	private String permCode;                // 按钮权限标识
	private String type;                    // 菜单类型
	private Integer isHidden;               // 是否隐藏

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
	 * 得到显示顺序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置显示顺序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * 得到按钮权限标识
	 * @return
	 */
	public String getPermCode() {
		return permCode;
	}
	/**
	 * 设置按钮权限标识
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


}
