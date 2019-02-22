package cn.jsonXxxx.jyTest.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.jsonXxxx.jyTest.entity.Menu;
import cn.jsonXxxx.jyTest.mapper.MenuMapper;
import cn.jsonXxxx.jyTest.service.IMenuService;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
