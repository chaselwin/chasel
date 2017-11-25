package com.chasel.family.controller;

import static com.chasel.family.constant.MessagesConstant.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chasel.common.constant.BaseController;
import com.chasel.common.constant.ResponseResult;
import com.chasel.family.service.IImageService;
import com.chasel.family.vo.Image;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("处理图片接口文档")
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

	@Autowired
	private IImageService imageService;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ApiOperation("图片分页查询")
	@RequestMapping(path = "/list/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PageInfo<?> listPage(@RequestBody Image image, PageInfo<Image> pageInfo) {

		return doQuery(() -> {
			return imageService.listPage(image, pageInfo);
		});
	}

	/** 查询所有 **/
	@ApiOperation("查询所有图片")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseResult findAll() {

		return value(() -> {return imageService.findAll(new Image());}, QUERY_SUCCESS, QUERY_FAIL);
	}

	/**
	 * 添加照片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("上传图片")
	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult save(@RequestParam(value = "type") String type,
			@RequestParam(value = "creator") String creator, @RequestParam(value = "memo") String memo,
			@RequestParam(value = "title") String title, @RequestParam(value = "file") MultipartFile file) {

		return process(() -> {imageService.save(file, type, title, memo, creator);}, ADD_SUCCESS, ADD_FAIL);
	}

	/** 删除文件 **/
	@ApiOperation("删除图片")
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseResult delete(@PathVariable @Validated @Valid int id) {

		return process(() -> {imageService.delete(id);}, DEL_SUCCESS, DEL_FAIL);
	}

	/**
	 * 编辑照片
	 * 
	 * @param image
	 * @return
	 */
	@ApiOperation("修改图片名称")
	@RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseResult update(@RequestBody Image image) {

		return process(() -> {imageService.update(image);}, UPDATE_SUCCESS, UPDATE_FAIL);
	}

}
