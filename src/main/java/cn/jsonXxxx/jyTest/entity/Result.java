package cn.jsonXxxx.jyTest.entity;

import cn.jsonXxxx.jyTest.utils.JsonUtils;

public class Result {
	private int code;
	private String msg;
	private Object data;
	private Object contentManagement;

	public Result(RespCode respCode) {
		this.code = respCode.getCode();
		this.msg = respCode.getMsg();
	}

	public Result(RespCode respCode, Object data, Object contentManagement) {
		this(respCode);
		this.data = data;
		this.contentManagement = contentManagement;
	}

	public Result(RespCode respCode, Object data, String msg, Object contentManagement) {
		this(respCode);
		this.msg = msg;
		this.data = data;
		this.contentManagement = contentManagement;
	}

	public Object getContentManagement() {
		return contentManagement;
	}

	public void setContentManagement(Object contentManagement) {
		this.contentManagement = contentManagement;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result addData(Object data) {
		this.data = data;
		return this;
	}

	public Result addContentManagement(Object contentManagement) {
		this.contentManagement = contentManagement;
		return this;
	}

	public Result addMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public static Result SUCCESS() {
		return new Result(RespCode.SUCCESS);
	}

	public static Result ERROR() {
		return new Result(RespCode.ERROR);
	}

	public static Result WARN() {
		return new Result(RespCode.WARN);
	}

	@Override
	public String toString() {
		try {
			return JsonUtils.write(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
