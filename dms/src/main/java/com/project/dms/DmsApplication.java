package com.project.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DmsApplication.class, args);
	}

	    //为springboot打包项目用的
	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        // TODO Auto-generated method stub
	        return builder.sources(this.getClass());
	    }
}
