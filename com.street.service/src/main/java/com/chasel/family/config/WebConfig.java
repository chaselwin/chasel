/*
 * package com.chasel.family.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.ViewResolver; import
 * org.springframework.web.servlet.config.annotation.
 * DefaultServletHandlerConfigurer; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc; import
 * org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 * import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 * import org.springframework.web.servlet.view.InternalResourceViewResolver;
 * 
 * @Configuration
 * 
 * @EnableWebMvc public class WebConfig extends WebMvcConfigurerAdapter {
 * 
 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
 * resolver = new InternalResourceViewResolver();
 * resolver.setPrefix("WEB-INF/views/"); resolver.setSuffix(".html");
 * resolver.setExposeContextBeansAsAttributes(true); return resolver; }
 * 
 * @Override public void
 * configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
 * configurer.enable(); }
 * 
 * //处理静态资源js、css等等
 * 
 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/street/**").addResourceLocations(
 * "classpath:/street/"); super.addResourceHandlers(registry); } }
 */