package cn.jsonXxxx.jyTest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.mapper.RoleMapper;
import cn.jsonXxxx.jyTest.service.IRoleService;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
