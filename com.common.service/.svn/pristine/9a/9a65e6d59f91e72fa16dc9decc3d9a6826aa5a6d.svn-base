package com.chasel.common.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.chasel.common.exception.DuplicateRecordException;

/**
 * 导出（下载）文件公共底层服务
 * 
 * @author chasel
 *
 */
public interface IExportBaseService {

	/**
	 * 通用下载接口
	 * 
	 * @param bytes
	 * @param fileName
	 */
	public void downloadFile(byte[] bytes, String fileName) throws DuplicateRecordException;

	/**
	 * Excel专用下载接口
	 * 
	 * @param workBook
	 */
	public void downloadFile(HSSFWorkbook workBook, String fileName) throws DuplicateRecordException;
}
