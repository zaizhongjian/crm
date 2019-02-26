package cn.jsonXxxx.jyTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.jsonXxxx.jyTest.entity.User;
import cn.jsonXxxx.jyTest.service.IUserService;

/**
 * @author jsonXxxx 主要返回页面
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/user")
	public String userIndex(Model model) {
		return "/page/user/userList.html";
	}

	@RequestMapping("/role")
	public String roleIndex() {
		return "/page/404";
	}

	@RequestMapping("/menu")
	public String meneIndex() {
		return "/page/404";
	}

	@RequestMapping("/to404")
	public String to404Index() {
		return "/page/404";
	}

	@RequestMapping("/main")
	public String toWelcome() {
		return "/page/main";
	}

}
