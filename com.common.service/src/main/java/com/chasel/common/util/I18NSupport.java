package com.chasel.common.util;

public class I18NSupport {

	public String getMassage(String code, Object... args) {
		return ResourceUtil.getMassage(code, args);
	}

	public String getUrl() {
		return ResourceUtil.getUrl();
	}

	public String getLanguageCode() {
		return ResourceUtil.getLanguageCode();
	}
}
