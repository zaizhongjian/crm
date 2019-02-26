package cn.jsonXxxx.jyTest.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.query.BaseQuery;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface IUserService extends IService<User> {
	/**
	 * 根据用户名查询用户（权限认证时候使用的）
	 * 
	 * @param username
	 * @return
	 */
	User getByUsername(String username);

	/**
	 * 获取用户根据查询条件。可以匹配模糊查询
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageList<User> findAll(BaseQuery query);

}
