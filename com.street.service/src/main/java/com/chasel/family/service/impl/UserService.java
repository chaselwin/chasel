package com.chasel.family.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chasel.common.constant.CodeConstants;
import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.family.dao.IUserDao;
import com.chasel.family.service.IUserService;
import com.chasel.family.vo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService implements IUserService {

	private static final String MSG_ID_NULL = "用户ID不能为空";
	private static final String MSG_USER_NULL = "用户信息不能为空";

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) throws DuplicateRecordException {
		if (user == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_USER_NULL);
		}
		userDao.save(user);
	}

	@Override
	public void delete(int id) throws DuplicateRecordException {
		if (findById(id) == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_ID_NULL);
		} else {
			userDao.delete(id);
		}
	}

	@Override
	public void update(User user) throws DuplicateRecordException {
		if (user == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_USER_NULL);
		} else {
			userDao.update(user);
		}
	}

	@Override
	public PageInfo<User> findAll(User user, PageInfo<User> pageInfo) throws DuplicateRecordException {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<User> list = userDao.findAll(user);
		return new PageInfo<User>(list);
	}

	@Override
	public List<User> findById(int id) throws DuplicateRecordException {
		User user = userDao.findById(id);
		List<User> list = new ArrayList<>();
		list.add(user);
		return list;
	}

	@Override
	public User login(String account, String password) {

		return userDao.login(account, password);
	}

}
