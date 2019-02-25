package cn.jsonXxxx.jyTest.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jsonXxxx.jyTest.entity.Menu;
import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.service.IMenuService;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author jsonXxxx
 * @param <E>
 * @since 2019-02-21
 */
@RestController
@RequestMapping("/menu")
public class MenuController<E> {
	@Autowired
	private IMenuService service;

	@RequestMapping("/findByUsername")
	public Result findByUsername() {
		// 从shiro中获取认证的主题（username）
		String principal = (String) SecurityUtils.getSubject().getPrincipal();
		// 获取该用户所有的menu
		List<Menu> menuList = service.findMenuByUsername(principal);
		// 遍历父菜单，过滤所有为空的对象
		menuList = menuList.stream().filter(menu -> Objects.nonNull(menu)).collect(Collectors.toList());
		// 拿到子菜单，判断url是否为空
		return Result.SUCCESS().addData(menuList);
	}

}
