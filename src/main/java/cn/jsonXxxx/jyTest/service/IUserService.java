package cn.jsonXxxx.jyTest.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.jsonXxxx.jyTest.entity.User;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface IUserService extends IService<User> {
	User getByUsername(String username);
}
