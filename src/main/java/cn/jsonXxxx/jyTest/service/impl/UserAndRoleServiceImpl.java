package cn.jsonXxxx.jyTest.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.entity.UserRole;
import cn.jsonXxxx.jyTest.mapper.UserMapper;
import cn.jsonXxxx.jyTest.mapper.UserRoleMapper;
import cn.jsonXxxx.jyTest.service.IUserAndRoleService;
import cn.jsonXxxx.jyTest.service.IUserRoleService;
import cn.jsonXxxx.jyTest.service.IUserService;
import cn.jsonXxxx.jyTest.shiro.PasswordHelper;

@Service
public class UserAndRoleServiceImpl implements IUserAndRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UserAndRoleServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 使用事务。两条sql。必须都成功
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUserAndRole(User user, Long[] roleIds) {
		try {
			Long userId = user.getUserId();
			// 标志，是否需要重新插入中间表的数据,false为不需要
			Boolean flag = true;
			// 如果是新增的话，默认密码为admin
			if (userId == null) {
				PasswordHelper passwordHelper = new PasswordHelper();
				user.setPassword("admin");
				passwordHelper.encryptPassword(user);
				user.setCreateTime(new Date());
				// 成功的条数
				userMapper.insertOneUser(user);
				// 这里才是返回的主键id
				userId = user.getUserId();
			} else {
				// 如果进入，则说明是修改，标志改为false
				userMapper.updateById(user);
				flag = false;
				userId = user.getUserId();
				List<UserRole> list = userRoleMapper
						.selectList(new QueryWrapper<UserRole>().eq("user_id", user.getUserId()));
				// 所含有的中间表的角色id
				List<Long> rolesId = list.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());
				// 对应中间表的id
				// 如果传入的和查出来的不相同，那么删除后重新插入
				if (!Objects.equals(rolesId, Arrays.asList(roleIds))) {
					flag = true;
					list.stream().map(userRole -> userRole.getId()).forEach(id -> {
						userRoleMapper.deleteById(id);
					});
				}
			}
			// 只要进入，那么是新增，或者是已经修改过
			if (flag) {
				for (Long roleId : roleIds) {
					UserRole userRole = new UserRole();
					userRole.setRoleId(roleId);
					userRole.setUserId(userId);
					userRoleMapper.insert(userRole);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserAndRoleServiceImpl-->insertUserAndRole" + e.getMessage(), e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result removeUserAndRole(Long userId) {
		try {
			// 先删除用户信息
			userMapper.deleteById(userId);
			// 再删除用户与角色的中间表信息
			userRoleMapper.deleteUserAndRole(userId);
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserAndRoleServiceImpl-->removeUserAndRole" + e.getMessage(), e);
			return Result.ERROR();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result removeAllUserAndRole(List<Long> userIds) {
		try {
			userIds.stream().forEach(userId -> {
				userMapper.deleteById(userId);
				userRoleMapper.deleteUserAndRole(userId);
			});
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserAndRoleServiceImpl-->removeAllUserAndRole" + e.getMessage(), e);
			return Result.ERROR();
		}
	}

}
