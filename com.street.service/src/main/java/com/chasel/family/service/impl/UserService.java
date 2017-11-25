package com.chasel.family.service.impl;

import static com.chasel.family.constant.MessagesConstant.ACCONT_FAIL;
import static com.chasel.family.constant.MessagesConstant.NOT_LOGIN;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chasel.common.constant.CodeConstants;
import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.service.impl.BaseService;
import com.chasel.family.dao.IUserDao;
import com.chasel.family.service.IUserService;
import com.chasel.family.vo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService extends BaseService implements IUserService {

	private static final String MSG_ID_NULL = "用户ID不能为空";
	private static final String MSG_USER_NULL = "用户信息不能为空";
	private static final String USER_NAME = "userName";

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) throws DuplicateRecordException {
		if (user == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
		}
		userDao.save(user);
	}

	@Override
	public void delete(int id) throws DuplicateRecordException {
		if (findById(id) == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_ID_NULL);
		} else {
			userDao.delete(id);
		}
	}

	@Override
	public void update(User user) throws DuplicateRecordException {
		if (user == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_USER_NULL);
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
	public void login(String account, String password, HttpSession httpSession) throws DuplicateRecordException {
		User newUser = userDao.login(account, password);
		if (newUser == null) {
			throw new DuplicateRecordException(ACCONT_FAIL);
		}
		httpSession.setAttribute(USER_NAME, newUser.getName());
	}

	@Override
	public void isLogin(HttpSession httpSession) throws DuplicateRecordException {
		String userName = (String) httpSession.getAttribute(USER_NAME);
		if (StringUtils.isEmpty(userName)) {
			throw new DuplicateRecordException(getMassage(NOT_LOGIN));
		}
	}

	@Override
	public void logout(HttpSession httpSession) {
		httpSession.removeAttribute(USER_NAME);
	}

}
