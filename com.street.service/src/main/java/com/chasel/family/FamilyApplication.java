package com.chasel.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot启动程序
 * 
 * @author street
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.chasel.**" })
public class FamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyApplication.class, args);
	}

}
