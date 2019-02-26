package cn.jsonXxxx.jyTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jsonXxxx 主要返回页面
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/user")
	public String userIndex() {
		return "/page/user/userList";
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

	@RequestMapping("/toUserAdd")
	public String toadd() {
		return "/page/user/userAdd";
	}
}
