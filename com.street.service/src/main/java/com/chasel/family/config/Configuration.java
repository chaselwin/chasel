package com.chasel.family.config;

import static com.chasel.family.constant.MessagesConstant.FAMILY;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class Configuration implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setContextPath("/" + FAMILY);
		container.setPort(8080);
		container.setSessionTimeout(30);
	}

}
