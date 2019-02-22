package cn.jsonXxxx.jyTest.utils;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("ExcludesFilter")
public interface ExcludesFilteMixIn {
	// 用来标记排除的接口
}
