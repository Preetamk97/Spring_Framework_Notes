package com.spring.basics.spring_basics.complexScope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringComplexScopeApplication {

	// Creating a Logger Object for Logging the Output (// Good Coding Practise -
	// Better Alternative of 'sysout')
	// Keep in mind to import the 'org.slf4j.Logger'
	private static Logger logger = LoggerFactory.getLogger(SpringComplexScopeApplication.class);

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringComplexScopeApplication.class, args);

		// In this demostration, we have kept the scope of PersonDao Component as 'Singleton'. (Only one instance for the entire SpringComplexScopeApplication)
		// And also we have set the scope of JdbcConnection component (which is Autowired in PersonDao Component) as '@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)'. (New instance everytime - despite being autowired to a Singleton scope component )
		// Concept Illustrated Below.

		// Creating a Bean 1 of PersonDao Component
		PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);

		// Creating a Bean 2 of PersonDao Component
		PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

		// Logging the PersonDAO Beans created
		logger.info("{}", personDAO1); // Same as sysout(personDAO1);
		logger.info("{}", personDAO2); // Same as sysout(personDAO1);
		// Output:
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2c5d601e 
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2c5d601e
		// Same Beans - Singleton Scope Component

		// Logging the JdbcConnection Beans created for each PersonDAO Bean
		logger.info("{}", personDAO1.jdbc_Connection);
		logger.info("{}", personDAO2.jdbc_Connection);
		logger.info("{}", personDAO2.jdbc_Connection);
		// Output
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@263f04ca
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@2ca47471
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@72efb5c1
		// Different Beans - Prototype Scope Bean (@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
		// Also note that here, we logged the 'jdbc_connection' attribute of the 'personDAO2' bean 2 times - and each time, we got a different bean of 'jdbc_Connection'.
	}
}
