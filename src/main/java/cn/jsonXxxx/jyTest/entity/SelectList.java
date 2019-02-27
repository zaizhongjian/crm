package cn.jsonXxxx.jyTest.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * select进行封装
 * 
 * @author JsonXxxx
 * @param <T>
 *
 */

//{
//    "code": 0,
//    "msg": "success",
//    "data": [
//        {"name":"北京","value":1,"selected":"","disabled":""},
//        {"name":"上海","value":2,"selected":"","disabled":""},
//        {"name":"广州","value":3,"selected":"selected","disabled":""},
//        {"name":"深圳","value":4,"selected":"","disabled":"disabled"},
//        {"name":"天津","value":5,"selected":"","disabled":""}
//    ]
//}
public class SelectList {
	private Integer code;
	private String msg;
	private List<SelectData> data = new ArrayList<SelectData>();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<SelectData> getData() {
		return data;
	}

	public void setData(List<SelectData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SelectList [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
