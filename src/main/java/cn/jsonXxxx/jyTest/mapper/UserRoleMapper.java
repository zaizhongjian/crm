package cn.jsonXxxx.jyTest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.jsonXxxx.jyTest.entity.UserRole;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
	Integer deleteUserAndRole(Long userId);
}
