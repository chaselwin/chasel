package com.chasel.family.dao;

import org.apache.ibatis.annotations.Mapper;

import com.chasel.common.dao.IBaseDao;
import com.chasel.family.vo.Image;

@Mapper
public interface IImageDao extends IBaseDao<Image> {

}
