package com.chasel.common.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.service.IExportBaseService;
import com.chasel.common.util.IoUtils;

@Service
public class ExportBaseService implements IExportBaseService {

	@Override
	public void downloadFile(byte[] bytes, String fileName) throws DuplicateRecordException {
		try {
			IoUtils.downloadFile(bytes, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downloadFile(HSSFWorkbook workBook, String fileName) throws DuplicateRecordException {
		try {
			IoUtils.downloadFile(workBook, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
