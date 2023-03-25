package com.spring.basics.spring_basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.spring.basics.componentScan.ComponentDAO;
@SpringBootApplication
@ComponentScan("com.spring.basics.componentScan")  // Inside @ComponentScan, add the address of the package (componentScan) that contains the class ComponentDAO.java and its dependency classes that we wanna use for this Spring Application.
public class SpringComponentScanApplication {

	// Creating a Logger Object for Logging the Output (// Good Coding Practise -
	// Better Alternative of 'sysout')
	// Keep in mind to import the 'org.slf4j.Logger'
	private static Logger logger = LoggerFactory.getLogger(SpringComponentScanApplication.class);

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringComponentScanApplication.class, args);

		// Creating a Bean of ComponentDao Component
		ComponentDAO componentDAO = applicationContext.getBean(ComponentDAO.class);

		// Logging the ComponentDAO Bean created
		logger.info("{}", componentDAO); // Same as sysout(componentDAO);

	}
}
