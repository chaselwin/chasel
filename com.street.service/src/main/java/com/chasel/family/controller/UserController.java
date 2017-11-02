package com.chasel.family.controller;

import static com.chasel.family.constant.FamilyConstant.ADD_FAIL;
import static com.chasel.family.constant.FamilyConstant.ADD_SUCCESS;
import static com.chasel.family.constant.FamilyConstant.DEL_FAIL;
import static com.chasel.family.constant.FamilyConstant.DEL_SUCCESS;
import static com.chasel.family.constant.FamilyConstant.QUERY_FAIL;
import static com.chasel.family.constant.FamilyConstant.QUERY_SUCCESS;
import static com.chasel.family.constant.FamilyConstant.UPDATE_FAIL;
import static com.chasel.family.constant.FamilyConstant.UPDATE_SUCCESS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chasel.common.constant.BaseController;
import com.chasel.common.constant.ResponseResult;
import com.chasel.family.service.IUserService;
import com.chasel.family.vo.User;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping(path = "/list/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PageInfo<?> findAll(@RequestBody User user, PageInfo<User> pageInfo) {

		return doQuery(() -> {
			return userService.findAll(user, pageInfo);
		});
	}

	/**
	 * 添加用户
	 * 
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult save(@RequestBody @Validated @Valid User user) {

		return process(() -> {
			userService.save(user);
		}, ADD_SUCCESS, ADD_FAIL);
	}

	/**
	 * 根据id查询用户
	 * 
	 */
	@RequestMapping(path = "/findUser/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseResult findById(@PathVariable int id) {

		return value(() -> {
			return userService.findById(id);
		}, QUERY_SUCCESS, QUERY_FAIL);
	}

	/**
	 * 删除用户
	 * 
	 */
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseResult delete(@PathVariable @Validated @Valid int id) {

		return process(() -> {
			userService.delete(id);
		}, DEL_SUCCESS, DEL_FAIL);
	}

	/**
	 * 编辑用户
	 * 
	 */
	@RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseResult update(@RequestBody @Validated @Valid User user) {

		return process(() -> {
			userService.update(user);
		}, UPDATE_SUCCESS, UPDATE_FAIL);
	}

}
