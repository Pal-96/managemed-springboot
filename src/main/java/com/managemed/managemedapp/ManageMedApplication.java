package com.managemed.managemedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

// @SpringBootApplication
// public class ManagemedappApplication {

// 	public static void main(String[] args) {
// 		ApplicationContext context = SpringApplication.run(ManagemedappApplication.class, args);

// 		Dev obj = context.getBean(Dev.class);
// 		obj.build();
// 	}

// }

@SpringBootApplication
public class ManageMedApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ManageMedApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ManageMedApplication.class, args);
    }
}
