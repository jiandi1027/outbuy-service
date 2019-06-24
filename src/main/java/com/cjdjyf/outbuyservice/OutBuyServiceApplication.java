package com.cjdjyf.outbuyservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.cjdjyf.outbuyservice")
@MapperScan("com.cjdjyf.outbuyservice.mapper")
public class OutBuyServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OutBuyServiceApplication.class, args);
	}
}
