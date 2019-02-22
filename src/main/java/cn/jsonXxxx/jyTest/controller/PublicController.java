package cn.jsonXxxx.jyTest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jsonXxxx.jyTest.config.GeetestConfig;
import cn.jsonXxxx.jyTest.sdk.GeetestLib;
import cn.jsonXxxx.jyTest.utils.IpUtil;

@Controller
@RequestMapping("/public")
public class PublicController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
				GeetestConfig.isnewfailback());
		String resStr = "{}";
		// 自定义userid
		HttpSession session = request.getSession();

		// User baseUser = (User) session.getAttribute("baseUser");

		// String userid = null;
		// if (Objects.nonNull(baseUser)) {
		// userid = baseUser.getUserName();
		// }
		// 自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_id", "username"); // 网站用户id
		param.put("ip_address", IpUtil.getIpAddr(request)); // 传输用户请求验证时所携带的IP

		// 进行验证预处理
		int gtServerStatus = gtSdk.preProcess(param);
		// 将服务器状态设置到session中
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		// 将userid设置到session中
		request.getSession().setAttribute("userid", "username");
		resStr = gtSdk.getResponseStr();
		PrintWriter out = response.getWriter();
		out.println(resStr);
	}
}
