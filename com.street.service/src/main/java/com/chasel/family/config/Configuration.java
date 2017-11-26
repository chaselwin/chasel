package com.chasel.family.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "family.app", ignoreUnknownFields = false)
public class Configuration implements EmbeddedServletContainerCustomizer {
	
	private static final Integer sessionTimeOut=30;
	
	private String contextPath;
	
	private Integer port;
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setContextPath(contextPath);
		container.setPort(port);
		container.setSessionTimeout(sessionTimeOut);
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	

}
