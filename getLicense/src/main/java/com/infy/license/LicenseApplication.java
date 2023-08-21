package com.infy.license;

import com.infy.license.controller.LicenseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({LicenseController.class})
public class LicenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}
}
