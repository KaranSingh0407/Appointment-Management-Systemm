package com.cg.hcms.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AppointmentManagementSystemApplication {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		SpringApplication.run(AppointmentManagementSystemApplication.class, args);
	}

}
