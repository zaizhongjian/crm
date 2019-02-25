package cn.jsonXxxx.jyTest.controller;

import java.util.List;
import java.util.Objects;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.service.IUserService;
import cn.jsonXxxx.jyTest.shiro.PasswordHelper;

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
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService service;

	/**
	 * 查询所有的用户，并且有带有"user:list"权限才能访问
	 * 
	 * @return
	 */
	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	public List<User> findAll() {
		List<User> list = service.list(new QueryWrapper<User>());
		return list;
	}

	@RequestMapping("/insertOrUpdate")
	public Result insertOrUpdate(User user) {
		// 判断用户是否为空
		if (Objects.isNull(user)) {
			return Result.ERROR().addMsg("用户验证不通过");
		}
		PasswordHelper passwordHelper = new PasswordHelper();
		passwordHelper.encryptPassword(user);
		try {
			service.saveOrUpdate(user);
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserController-->insertOrUpdate()" + e.getMessage(), e);
			return Result.ERROR().addMsg("操作失败");
		}

	}

	@RequestMapping("/delete")
	public Result deleteOne(Long userId) {
		// 判断用户是否为空
		if (null == userId) {
			return Result.ERROR().addMsg("用户id不能为空");
		}
		try {
			service.removeById(userId);
			return Result.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserController-->insertOrUpdate()" + e.getMessage(), e);
			return Result.ERROR().addMsg("操作失败");
		}

	}

	@RequestMapping("/findMenuByUser")
	public Result findMenuByUser() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();

		return null;
	}

}
