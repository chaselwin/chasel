package com.chasel.family.controller;

import static com.chasel.family.constant.MessagesConstant.ACCONT_SUCCESS;
import static com.chasel.family.constant.MessagesConstant.ADD_FAIL;
import static com.chasel.family.constant.MessagesConstant.HAS_LOGIN;
import static com.chasel.family.constant.MessagesConstant.LOGOUT_SUCCESS;
import static com.chasel.family.constant.MessagesConstant.NOT_LOGIN;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chasel.common.constant.BaseController;
import com.chasel.common.constant.ResponseResult;
import com.chasel.family.service.IUserService;
import com.chasel.family.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("登录信息")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

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
	@ApiOperation("用户登录")
	@RequestMapping(path = "/userLogin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult login(@RequestBody User user, HttpSession httpSession) throws JSONException {

		return process(() -> userService.login(user.getAccount(), user.getPassword(), httpSession),
				getMassage(ACCONT_SUCCESS), getMassage(ADD_FAIL));
	}

	/**
	 * 登录状态
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@ApiOperation("是否登录")
	@RequestMapping(path = "/isLogin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult isLogin(HttpSession httpSession) throws JSONException {

		return process(() -> userService.isLogin(httpSession), getMassage(HAS_LOGIN), getMassage(NOT_LOGIN));
	}

	/**
	 * 注销
	 * 
	 * @param httpSession
	 * @return
	 * @throws JSONException
	 */
	@ApiOperation("注销登录")
	@RequestMapping(path = "/logout", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult logout(HttpSession httpSession) throws JSONException {

		return process(() -> userService.logout(httpSession), getMassage(LOGOUT_SUCCESS), getMassage(LOGOUT_SUCCESS));
	}

}
