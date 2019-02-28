package cn.jsonXxxx.jyTest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.entity.UserRole;
import cn.jsonXxxx.jyTest.mapper.UserMapper;
import cn.jsonXxxx.jyTest.mapper.UserRoleMapper;
import cn.jsonXxxx.jyTest.query.BaseQuery;
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

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User getByUsername(String username) {
		User byUsername = mapper.getByUsername(username);
		return byUsername;
	}

	@Override
	public PageList<User> findAll(BaseQuery query) {
		// 分页对象
		IPage<User> page = new Page<User>(query.getPage(), query.getLimit());
		IPage<User> selectPage = null;
		// 传回前台的分页对象
		PageList<User> pageList = new PageList<User>();
		// 查询条件的封装
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		// 关键字
		String key = query.getKey();
		if (StringUtils.isNotBlank(key)) {
			queryWrapper.like("user_id", key).or().like("username", key).or().like("email", key).or().like("mobile",
					key);
		}

		try {
			selectPage = mapper.selectPage(page, queryWrapper);
			pageList.setCode(0L);
		} catch (Exception e) {
			e.printStackTrace();
			pageList.setMsg("操作失败");
		}
		List<User> records = selectPage.getRecords();
		records.stream().forEach(user -> {
			List<UserRole> selectList = userRoleMapper
					.selectList(new QueryWrapper<UserRole>().eq("user_id", user.getUserId()));
			user.setRoleIds(selectList.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList()));
		});
		pageList.setData(records);
		pageList.setCount(selectPage.getTotal());
		return pageList;
	}

	@Override
	public Long insertOneUser(User user) {
		return mapper.insertOneUser(user);
	}

}
