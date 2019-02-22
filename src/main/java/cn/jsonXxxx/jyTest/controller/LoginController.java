package cn.jsonXxxx.jyTest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jsonXxxx.jyTest.entity.Result;
import cn.jsonXxxx.jyTest.utils.Verification;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	@ResponseBody
	public Result LoginTest(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// 返回的状态
		int verification = Verification.verification(request, response);
		// 极验成功之后
		if (verification == Verification.SUCCESS) {
			Subject currentUser = SecurityUtils.getSubject();
			// 验证用户是否验证，即是否登录
			if (!currentUser.isAuthenticated()) {
				String msg = "";
				// 把用户名和密码封装为 UsernamePasswordToken 对象
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				// remembermMe记住密码
				token.setRememberMe(true);
				try {
					// 执行登录.
					currentUser.login(token);
					// 登录成功...
					return Result.SUCCESS();
				} catch (IncorrectCredentialsException e) {
					msg = "登录密码错误";
				} catch (ExcessiveAttemptsException e) {
					msg = "登录失败次数过多";
					System.out.println("登录失败次数过多!!!" + e);
				} catch (LockedAccountException e) {
					msg = "帐号已被锁定";
					System.out.println("帐号已被锁定!!!" + e);
				} catch (DisabledAccountException e) {
					msg = "帐号已被禁用";
					System.out.println("帐号已被禁用!!!" + e);
				} catch (ExpiredCredentialsException e) {
					msg = "帐号已过期";
					System.out.println("帐号已过期!!!" + e);
				} catch (UnknownAccountException e) {
					msg = "帐号不存在";
					System.out.println("帐号不存在!!!" + e);
				} catch (UnauthorizedException e) {
					msg = "您没有得到相应的授权！";
					System.out.println("您没有得到相应的授权！" + e);
				} catch (Exception e) {
					System.out.println("出错！！！" + e);
				}
			}
		}
		return Result.SUCCESS();
	}
}
