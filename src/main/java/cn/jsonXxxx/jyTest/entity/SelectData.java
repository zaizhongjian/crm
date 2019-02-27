package cn.jsonXxxx.jyTest.entity;

/**
 * 按格式封装
 * 
 * @author jsonXxxx
 *
 */
public class SelectData {
	// {"name":"北京","value":1,"selected":"","disabled":""},
	// {"name":"上海","value":2,"selected":"","disabled":""},
	// {"name":"广州","value":3,"selected":"selected","disabled":""},
	// {"name":"深圳","value":4,"selected":"","disabled":"disabled"},
	// {"name":"天津","value":5,"selected":"","disabled":""}
	private String name;
	private Long value;
	private String selected = "";
	private String disabled = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toString() {
		return "SelectData [name=" + name + ", value=" + value + ", selected=" + selected + ", disabled=" + disabled
				+ "]";
	}

}
