package cn.jsonXxxx.jyTest.entity;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
	// 总条数
	private Long count;
	// 状态码 0--》成功，1--》失败
	private Long code = 0L;
	// 返回消息,只有错误的时候才修改
	private String msg;
	// 所有数据
	private List<T> data = new ArrayList<>();

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		this.code = 1L;
	}

}
