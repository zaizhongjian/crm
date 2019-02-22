package cn.jsonXxxx.jyTest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import cn.jsonXxxx.jyTest.entity.Menu;
import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.Role;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.service.IUserService;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author jsonXxxx
 * @since 2019-02-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	public List<User> findAll() {
		List<User> list = service.list(new QueryWrapper<User>());
		return list;
	}

	@RequestMapping("/getByUsername")
	public User getByUsername(String username) {
		User byUsername = service.getByUsername(username);
		Set<String> roleNames = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		List<Role> roles = byUsername.getRoles();
		for (Role role : roles) {
			roleNames.add(role.getRoleName());
			List<Menu> menus = role.getMenus();
			menus.stream().forEach(menu -> {
				String perms = menu.getPerms();
				if (StringUtils.isNotBlank(perms)) {
					if (perms.contains(",")) {
						String[] split = perms.split(",");
						for (String str : split) {
							permissions.add(str);
						}
					} else {
						permissions.add(perms);
					}
				}
			});
		}
		System.out.println(roleNames);
		System.out.println(permissions);
		return service.getByUsername(username);
	}

}
