package cn.jsonXxxx.jyTest.service;

import java.util.List;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.Role;

public interface IRoleAndMenuService {
	public Result insertOrUpdateRole(Role role, List<Long> menuIds);
}
