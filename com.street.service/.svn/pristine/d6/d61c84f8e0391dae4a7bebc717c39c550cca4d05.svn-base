package com.chasel.family.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chasel.common.constant.CodeConstants;
import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.service.IMinioService;
import com.chasel.family.dao.IImageDao;
import com.chasel.family.service.IImageService;
import com.chasel.family.vo.Image;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ImageService implements IImageService {

	private static final String MSG_ID_NULL = "图片ID不能为空";
	private static final String MSG_IMAGE_NULL = "图片信息不能为空";
	public static final String FILE_NULL = "请选择文件";
	public static final String FILE_SIZE = "请选择小于5M图片";

	@Autowired
	private IImageDao imageDao;

	@Autowired
	private IMinioService minioService;

	@Override
	public void save(MultipartFile file, String type, String title, String memo, String creator)
			throws DuplicateRecordException {
		if (file == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, FILE_NULL);
		}
		if (file.getSize() >= 5242880) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, FILE_SIZE);
		} else {
			minioService.upload(file);
			Image image = new Image();
			image.setName(file.getOriginalFilename());
			image.setType(type);
			image.setTitle(title);
			image.setMemo(memo);
			image.setCreator(creator);
			image.setCreateTime(new Timestamp(System.currentTimeMillis()));
			imageDao.save(image);
		}
	}

	@Override
	public void delete(int id) throws DuplicateRecordException {
		if (findById(id) == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_ID_NULL);
		} else {
			Image image = findById(id);
			minioService.remove(image.getName());
			imageDao.delete(id);
		}
	}

	@Override
	public void update(Image image) throws DuplicateRecordException {

		if (findById(image.getId()) == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_ID_NULL);
		} else {
			imageDao.update(image);
		}

	}

	@Override
	public List<Image> findAll(Image imagev) throws DuplicateRecordException {
		List<Image> list = imageDao.findAll(imagev);
		List<Image> imageList = new ArrayList<Image>();
		for (Image image : list) {
			String imageName = image.getName();
			image.setName(minioService.getUrl(imageName));
			imageList.add(image);
		}
		return imageList;
	}

	@Override
	public Image findById(int id) throws DuplicateRecordException {
		return imageDao.findById(id);
	}

	@Override
	public PageInfo<Image> listPage(Image image, PageInfo<Image> pageInfo) throws DuplicateRecordException {
		if (image == null || pageInfo == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_403, MSG_IMAGE_NULL);
		}
		List<Image> list = new ArrayList<>();
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		list = imageDao.findAll(image);
		return new PageInfo<Image>(list);
	}

}
