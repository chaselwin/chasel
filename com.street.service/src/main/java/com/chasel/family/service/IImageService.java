package com.chasel.family.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.service.IBaseService;
import com.chasel.family.vo.Image;
import com.github.pagehelper.PageInfo;

public interface IImageService extends IBaseService<Image> {

	public void save(MultipartFile file, String type, String title, String memo, String creator)
			throws DuplicateRecordException;

	public void delete(int id) throws DuplicateRecordException;

	public void update(Image image) throws DuplicateRecordException;

	public List<Image> findAll(Image image) throws DuplicateRecordException;

	public Image findById(int id) throws DuplicateRecordException;

	PageInfo<Image> listPage(Image image, PageInfo<Image> pageInfo) throws DuplicateRecordException;

}
