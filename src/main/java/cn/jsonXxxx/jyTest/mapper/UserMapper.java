package cn.jsonXxxx.jyTest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.jsonXxxx.jyTest.entity.User;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface UserMapper extends BaseMapper<User> {

	User getByUsername(String username);
	
}
