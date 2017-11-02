package com.chasel.family.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chasel.family.service.IUserService;
import com.chasel.family.vo.User;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;

	/**
	 * 登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(path = "/userLogin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String login(@RequestBody User user, HttpSession httpSession) throws JSONException {
		User newUser = userService.login(user.getAccount(), user.getPassword());
		if (newUser != null) {
			httpSession.setAttribute("userName", newUser.getName());
			return new JSONObject().put("message", "欢迎回来").toString();
		} else {
			return new JSONObject().put("message", "账号或密码不正确").toString();
		}
	}

	/**
	 * 登录状态
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(path = "/ifLogin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String ifLogin(HttpSession httpSession) throws JSONException {
		String userName = (String) httpSession.getAttribute("userName");
		if (userName != null && userName != "") {
			return new JSONObject().put("message", "已登录").put("userName", userName).toString();
		} else {
			return new JSONObject().put("message", "未登录").toString();
		}
	}

	/**
	 * 注销
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String logout(HttpSession httpSession) throws JSONException {
		httpSession.removeAttribute("userName");
		return new JSONObject().put("message", "注销成功").toString();
	}

}
