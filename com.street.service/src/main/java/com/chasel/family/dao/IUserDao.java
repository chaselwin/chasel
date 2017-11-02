package com.chasel.family.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chasel.common.dao.IBaseDao;
import com.chasel.family.vo.User;

@Mapper
public interface IUserDao extends IBaseDao<User> {

	public User login(@Param("account") String account, @Param("password") String password);
}
