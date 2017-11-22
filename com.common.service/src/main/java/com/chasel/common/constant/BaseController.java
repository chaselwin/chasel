package com.chasel.common.constant;

import org.apache.log4j.Logger;

import com.chasel.common.exception.DuplicateRecordException;
import com.chasel.common.util.I18NSupport;
import com.github.pagehelper.PageInfo;

/**
 * 基础Controller
 * 
 * @author chasel
 *
 */
public abstract class BaseController extends I18NSupport {

	private Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 分页
	 * 
	 * @param run
	 * @return
	 */
	protected PageInfo<?> doQuery(LambCallable run) {
		PageInfo<?> pageInfo = new PageInfo<>();
		try {
			log.info("-----Welcome to doQuery-----");
			pageInfo = (PageInfo<?>) run.run();
		} catch (DuplicateRecordException e) {
			log.error("doQuery has an error: {} {}" + e.getErrCode() + "------and------" + e.getErrMsg());

		} catch (Exception e) {
			log.error("doQuery has an error: {}" + e.getMessage());

		}
		return pageInfo;
	}

	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult process(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to process-----");
			run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, successMsg);

		} catch (DuplicateRecordException e) {
			log.error("process has an error: {} {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("process has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, responseMsg);
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult value(LambCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to value-----");
			Object obj = run.run();
			return new ResponseResult(ResponseStatus.SUCCESS, successMsg, obj);
		} catch (DuplicateRecordException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("value has an error: {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
		} catch (Exception e) {
			log.error("value has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL, responseMsg, null);
	}
}
