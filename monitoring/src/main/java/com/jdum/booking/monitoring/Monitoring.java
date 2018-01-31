package com.jdum.booking.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Monitoring {

	public static void main(String[] args) {
		SpringApplication.run(Monitoring.class, args);
	}
}
