package com.yamdeng.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BootStandardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
		log.info("start BootStandardApplication !!!");
	}

}