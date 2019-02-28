package cn.jsonXxxx.jyTest.service;

import java.util.List;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.User;

public interface IUserAndRoleService {
	void insertUserAndRole(User user, Long roleIds[]);

	Result removeUserAndRole(Long userId);

	Result removeAllUserAndRole(List<Long> userIds);
}
