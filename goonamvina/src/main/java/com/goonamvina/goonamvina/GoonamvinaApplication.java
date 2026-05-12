package com.goonamvina.goonamvina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GoonamvinaApplication extends SpringBootServletInitializer {

	// Đây là phương thức cần thiết để cấu hình ứng dụng cho việc triển khai WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GoonamvinaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GoonamvinaApplication.class, args);
	}
}