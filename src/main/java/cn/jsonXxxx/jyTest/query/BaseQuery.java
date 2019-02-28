package cn.jsonXxxx.jyTest.query;

/**
 * 基本查询类，封装第几页，每页多少条，和关键字
 * 
 * @author jsonXxxx
 *
 */
public class BaseQuery {
	// 第几页
	private Integer page = 1;
	// 每页显示多少条
	private Integer limit = 10;
	// 关键字(模糊查询使用)
	private String key;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "BaseQuery [page=" + page + ", limit=" + limit + ", key=" + key + "]";
	}

}
