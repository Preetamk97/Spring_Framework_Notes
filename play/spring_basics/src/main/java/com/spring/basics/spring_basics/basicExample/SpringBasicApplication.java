package com.spring.basics.spring_basics.basicExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicApplication.class, args);

		BinarySearchImpl binarySearchImpl1 = applicationContext.getBean(BinarySearchImpl.class);

		System.out.println(binarySearchImpl1);

		int result = binarySearchImpl1.binarySearch(new int[] { 12, 4, 3 }, 3);

		System.out.println(result);
	}
}