package com.chasel.common.constant;

/**
 * 返回结果
 * 
 * @author chasel
 *
 */
public class ResponseResult {
	// 成功状态
	private ResponseStatus resultStatus;

	// 详细描述
	private String resultDesc;

	// 返回信息体
	private Object data;

	public ResponseResult() {
	}

	public ResponseResult(ResponseStatus resultStatus, String resultDesc) {
		super();
		this.resultStatus = resultStatus;
		this.resultDesc = resultDesc;
	}

	public ResponseResult(ResponseStatus resultStatus, String resultDesc, Object data) {
		super();
		this.resultStatus = resultStatus;
		this.resultDesc = resultDesc;
		this.data = data;
	}

	public ResponseStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResponseStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
