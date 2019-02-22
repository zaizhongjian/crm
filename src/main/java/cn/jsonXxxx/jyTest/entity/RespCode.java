package cn.jsonXxxx.jyTest.entity;

public enum RespCode {
	SUCCESS(0, "处理成功"), WARN(-1, "用户未登录，请稍后重试"), ERROR(1, "参数错误或者不符合要求！");

	private int code;
	private String msg;

	RespCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
