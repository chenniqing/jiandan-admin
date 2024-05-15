package cn.javaex.framework.model.entity;

import cn.javaex.mybatisjj.basic.annotation.TableId;
import java.time.LocalDateTime;

import cn.javaex.mybatisjj.basic.annotation.TableName;

/**
 * 菜单表
 * 
 * @author 陈霓清
 */
@TableName("sys_menu")
public class SysMenuEntity {
	@TableId(value = "id")
	private String id;                      // 主键
	private String parentId;                // 父级节点id
	private String name;                    // 菜单名称
	private String url;                     // 菜单链接
	private Integer sort;                   // 显示顺序
	private String icon;                    // 图标
	private String permCode;                // 按钮权限标识
	private String type;                    // 菜单类型
	private LocalDateTime createTime;       // 创建时间
	private String createBy;                // 创建人ID
	private LocalDateTime updateTime;       // 更新时间
	private String updateBy;                // 更新人ID
	private Integer isHidden;               // 是否隐藏
	private Integer isSystem;               // 是否系统内置菜单

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
	 * 得到创建时间
	 * @return
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	/**
	 * 得到创建人ID
	 * @return
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置创建人ID
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 得到更新时间
	 * @return
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 得到更新人ID
	 * @return
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置更新人ID
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
