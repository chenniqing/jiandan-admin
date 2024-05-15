package cn.javaex.framework.basic.error;

/**
 * 菜单相关错误
 * 
 * @author 陈霓清
 */
public enum MenuError implements CommonError {
	/**按钮标识重复*/
	MENU_1001(1001, "按钮标识重复"),
	/**导航必须要有链接地址*/
	MENU_1002(1002, "导航必须要有链接地址"),
	/**导航标识重复*/
	MENU_1003(1003, "导航标识重复"),
	/**目录或菜单必须要有父级节点*/
	MENU_1004(1004, "目录或菜单必须要有父级节点"),
	/**目录的父级节点必须是导航*/
	MENU_1005(1005, "目录的父级节点必须是导航"),
	/**菜单节点必须要有链接地址*/
	MENU_1006(1006, "菜单节点必须要有链接地址")
	;
	
	private final int code;
	private final String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	MenuError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
