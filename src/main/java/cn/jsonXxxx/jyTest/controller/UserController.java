package cn.jsonXxxx.jyTest.controller;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jsonXxxx.jyTest.entity.PageList;
import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.query.BaseQuery;
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
	public PageList<User> findAll(BaseQuery query) {
		return service.findAll(query);
	}

	@RequestMapping("/insertOrUpdate")
	public Result insertOrUpdate(User user) {
		// 判断密码是否为空
		// if (StringUtils.isBlank(password1)) {
		// return Result.ERROR().addMsg("密码不能为空");
		// }
		// if (StringUtils.isBlank(password2)) {
		// return Result.ERROR().addMsg("确认密码不能为空");
		// }
		// // 判读两次输入的密码是否想同
		// if (!Objects.equals(password1, password2)) {
		// return Result.ERROR().addMsg("两次密码不同");
		// }
		// 判断用户是否为空
		if (Objects.isNull(user)) {
			return Result.ERROR().addMsg("用户验证不通过");
		}
		// 默认密码admin(新增的情况)
		if (user.getUserId() == null) {
			PasswordHelper passwordHelper = new PasswordHelper();
			user.setPassword("admin");
			passwordHelper.encryptPassword(user);
			user.setCreateTime(new Date());
		}
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
}
