package com.yoel.fernandez.ApiAttendance;

import org.springframework.boot.SpringApplication;

public class TestApiAttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiAttendanceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
