Take a look at the following folder structure first:

![](imgfiles\chap18\2023-03-14-11-15-14.png)

In the following folder structure, we have a separate package **componentScan** that is outside the main **spring_basics** package. This package comprises of 2 classes namely - **ComponentDAO.java**, **ComponentJdbcConnecvtion.java**.

But the SpringBoot Application Class implementing this package namely - **SpringComponentScanApplication.java** is placed inside another package **spring_basics** - that is a **separate package outside the componentScan package**.

# Initial Codes - Before Using @ComponentScan - And the Problem:

```java
package com.spring.basics.spring_basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.basics.componentScan.ComponentDAO;

@SpringBootApplication
public class SpringComponentScanApplication {

	// Creating a Logger Object for Logging the Output (// Good Coding Practise -
	// Better Alternative of 'sysout')
	// Keep in mind to import the 'org.slf4j.Logger'
	private static Logger logger = LoggerFactory.getLogger(SpringComponentScanApplication.class);

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(SpringComponentScanApplication.class, args);

		// Creating a Bean of PersonDao Component
		ComponentDAO personDAO = applicationContext.getBean(ComponentDAO.class);

		// Logging the PersonDAO Bean created
		logger.info("{}", personDAO); // Same as sysout(personDAO1);

	}
}

//*************************************************

package com.spring.basics.componentScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentDAO { // DAO - Stands for Data Access Object.

    @Autowired
    ComponentJdbcConnection componentJdbcConnection; // Dependency Injection

    public ComponentDAO(ComponentJdbcConnection jdbc_Connection) {
        this.componentJdbcConnection = jdbc_Connection;
    }

    public ComponentJdbcConnection getJdbc_Connection() {
        return componentJdbcConnection;
    }

    public void setJdbc_Connection(ComponentJdbcConnection jdbc_Connection) {
        this.componentJdbcConnection = jdbc_Connection;
    }
}

//**************************************************

package com.spring.basics.componentScan;

import org.springframework.stereotype.Component;

@Component
public class ComponentJdbcConnection {

    public ComponentJdbcConnection() {
        System.out.println("JDBC Connection");
    }
}
```

If you try to execute the the above given code -  **keeping the file structure of the project exactly same as the given the ***Project Explorer File Structure*** above**, you will see no linter/syntax errors but when you execute the  **SpringComponentScanApplication.java**, you will see the following error message.

![](imgfiles\chap18\2023-03-14-11-32-52.png)

Here, the Spring is basically saying, that it cannot find/locate the class **ComponentDAO.java** for creating its **Bean** - because, this class is located in a different package (**componentScan**) that is located somewhere outside of the **spring_basics** package - in which our main  **SpringComponentScanApplication** class is located.

For solving this problem, we use the **@ComponentScan** Annotation.

# Solution of the Problem - After using @ComponentScan Annotation

```java
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

//*************************************************

// Classes ComponentDAO.java and ComponentJdbcConnection.java are same as above.
// No changes - Direct copy paste.
```

## Output:

![](imgfiles\chap18\2023-03-14-11-59-36.png)

This time, the output is perfect with no errors sowhat. That is due to the use of ***@ComponentScan("com.spring.basics.componentScan")*** annotation in the **SpringComponentScanApplication** as shown above.