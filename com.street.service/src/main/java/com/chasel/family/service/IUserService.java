package com.chasel.family.service;

import java.util.List;

import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.service.IBaseService;
import com.chasel.family.vo.User;
import com.github.pagehelper.PageInfo;

public interface IUserService extends IBaseService<User> {

	public User login(String account, String password);

	public PageInfo<User> findAll(User user, PageInfo<User> pageInfo) throws DuplicateRecordException;

	public void save(User user) throws DuplicateRecordException;

	public void delete(int id) throws DuplicateRecordException;

	public void update(User user) throws DuplicateRecordException;

	public List<User> findById(int id) throws DuplicateRecordException;
}
