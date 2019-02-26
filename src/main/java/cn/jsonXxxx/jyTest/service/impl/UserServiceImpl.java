package cn.jsonXxxx.jyTest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.PageList;
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

	@Override
	public PageList<User> findAll(Integer currentPage, Integer pageSize) {
		IPage<User> page = new Page<User>(currentPage, pageSize);
		IPage<User> selectPage = null;
		PageList<User> pageList = new PageList<User>();
		try {
			selectPage = mapper.selectPage(page, new QueryWrapper<User>());
			pageList.setCode(0L);
		} catch (Exception e) {
			e.printStackTrace();
			pageList.setMsg("操作失败");
		}
		pageList.setData(selectPage.getRecords());
		pageList.setCount(selectPage.getTotal());
		return pageList;
	}

}
