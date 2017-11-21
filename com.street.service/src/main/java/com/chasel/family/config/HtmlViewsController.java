/*package com.chasel.family.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

*//**
 * 拦截所有以.html结尾的请求，并根据逻辑视图名转发view
 * @author 
 *
 *//*
@Controller
public class HtmlViewsController {
    
	@RequestMapping("/{path:\\w+}.html")
	public String forwardToHtmlView(@PathVariable String path) {

		return path;
	}
}
*/