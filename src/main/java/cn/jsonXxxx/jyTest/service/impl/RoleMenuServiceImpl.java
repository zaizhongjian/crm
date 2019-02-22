package cn.jsonXxxx.jyTest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.RoleMenu;
import cn.jsonXxxx.jyTest.mapper.RoleMenuMapper;
import cn.jsonXxxx.jyTest.service.IRoleMenuService;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
