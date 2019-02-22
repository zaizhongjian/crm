package cn.jsonXxxx.jyTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.mapper.UserMapper;
import cn.jsonXxxx.jyTest.service.IUserService;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public User getByUsername(String username) {
		User byUsername = mapper.getByUsername(username);
		return byUsername;
	}

}
