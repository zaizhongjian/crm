package cn.jsonXxxx.jyTest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.UserRole;
import cn.jsonXxxx.jyTest.mapper.UserRoleMapper;
import cn.jsonXxxx.jyTest.service.IUserRoleService;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
