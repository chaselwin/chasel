package com.chasel.common.constant;

/**
 * 返回状态
 * 
 * @author chasel
 *
 */
public enum ResponseStatus {

	// 成功状态
	SUCCESS("SUCCESS"),

	// 失败状态
	FAIL("FAIL");

	private String code;

	public String getCode() {
		return code;
	}

	private ResponseStatus(String code) {
		this.code = code;
	}

}
