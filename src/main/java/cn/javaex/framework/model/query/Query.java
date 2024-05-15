package cn.javaex.framework.model.query;

/**
 * 查询类
 * 
 * @author 陈霓清
 */
public class Query {
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	private String keyword;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
