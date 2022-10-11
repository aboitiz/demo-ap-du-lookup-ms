package com.apc.du;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class DULookupServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(DULookupServiceApp.class, args);
		System.out.print(System.currentTimeMillis());

	}
}
