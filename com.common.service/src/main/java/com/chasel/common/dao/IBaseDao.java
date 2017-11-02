package com.chasel.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chasel.common.exception.DuplicateRecordException;

/**
 * Dao层基类
 * 
 * @author chasel
 *
 * @param <T>
 */
public interface IBaseDao<T> {

	public void save(T t) throws DuplicateRecordException;

	public void delete(@Param("id") int id) throws DuplicateRecordException;

	public void update(T t) throws DuplicateRecordException;

	public List<T> findAll(T t) throws DuplicateRecordException;

	public T findById(@Param("id") int id) throws DuplicateRecordException;
}
