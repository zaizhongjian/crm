package cn.jsonXxxx.jyTest.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.entity.UserRole;
import cn.jsonXxxx.jyTest.service.IUserAndRoleService;
import cn.jsonXxxx.jyTest.service.IUserRoleService;
import cn.jsonXxxx.jyTest.service.IUserService;
import cn.jsonXxxx.jyTest.shiro.PasswordHelper;

@Service
public class UserAndRoleServiceImpl implements IUserAndRoleService {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;

	/**
	 * 使用事务。两条sql。必须都成功
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUserAndRole(User user, Long[] roleIds) {
		try {
			Long userId = null;
			// 如果是新增的话，默认密码为admin
			if (user.getUserId() == null) {
				PasswordHelper passwordHelper = new PasswordHelper();
				user.setPassword("admin");
				passwordHelper.encryptPassword(user);
				user.setCreateTime(new Date());
			}
			// 如果是修改的话，那先删除中间表内容，然后在新增
			// 循环插入中间表
			if (user.getUserId() != null) {
				userId = user.getUserId();
				List<UserRole> list = userRoleService
						.list(new QueryWrapper<UserRole>().eq("user_id", user.getUserId()));
				List<Long> ids = list.stream().map(userRole -> userRole.getId()).collect(Collectors.toList());
				userRoleService.removeByIds(ids);
			}
			// 成功的条数
			Long insertOne = userService.insertOneUser(user);
			// 这里才是返回的主键id
			Long userId2 = user.getUserId();
			if (userId == null) {
				Arrays.asList(roleIds).stream().forEach(roleId -> {
					UserRole userRole = new UserRole();
					userRole.setUserId(userId2);
					userRole.setRoleId(roleId);
					userRoleService.save(userRole);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
