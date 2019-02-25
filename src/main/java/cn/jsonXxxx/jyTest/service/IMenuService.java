package cn.jsonXxxx.jyTest.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.jsonXxxx.jyTest.entity.Menu;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
public interface IMenuService extends IService<Menu> {
	/**
	 * 通过用户名查询菜单
	 * 
	 * @param username
	 * @return
	 */
	List<Menu> findMenuByUsername(String username);
}

