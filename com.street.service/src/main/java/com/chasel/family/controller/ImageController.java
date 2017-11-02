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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chasel.common.constant.BaseController;
import com.chasel.common.constant.ResponseResult;
import com.chasel.family.service.IImageService;
import com.chasel.family.vo.Image;
import com.github.pagehelper.PageInfo;

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
	@RequestMapping(path = "/list/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PageInfo<?> listPage(@RequestBody Image image, PageInfo<Image> pageInfo) {

		return doQuery(() -> {
			return imageService.listPage(image, pageInfo);
		});
	}

	/** 查询所有 **/
	@RequestMapping(path = "/findAll", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult frontAll() {

		return value(() -> {
			return imageService.findAll(new Image());
		}, QUERY_SUCCESS, QUERY_FAIL);
	}

	/**
	 * 添加照片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult save(@RequestParam(value = "type") String type,
			@RequestParam(value = "creator") String creator, @RequestParam(value = "memo") String memo,
			@RequestParam(value = "title") String title, @RequestParam(value = "file") MultipartFile file) {

		return process(() -> {
			imageService.save(file, type, title, memo, creator);
		}, ADD_SUCCESS, ADD_FAIL);
	}

	/** 删除文件 **/
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseResult delete(@PathVariable @Validated @Valid int id) {

		return process(() -> {
			imageService.delete(id);
		}, DEL_SUCCESS, DEL_FAIL);
	}

	/**
	 * 编辑照片
	 * 
	 * @param image
	 * @return
	 */
	@RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseResult update(@RequestBody @Validated @Valid Image image) {

		return process(() -> {
			imageService.update(image);
		}, UPDATE_SUCCESS, UPDATE_FAIL);
	}

}
