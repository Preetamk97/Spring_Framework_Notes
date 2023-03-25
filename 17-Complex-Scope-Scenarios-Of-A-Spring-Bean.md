# Case 1 : PersonDAO - Singleton Scope &&  JdbcConnection - Singleton Scope

```java
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

		// Creating a Bean 1 of PersonDao Component
		PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);

		// Creating a Bean 2 of PersonDao Component
		PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

		// Logging the PersonDAO Beans created
		logger.info("{}", personDAO1); // Same as sysout(personDAO1);
		logger.info("{}", personDAO2); // Same as sysout(personDAO1);
		// Output:
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2755d705 
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2755d705
		// Same Output - Singleton Scope Bean

		// Logging the JdbcConnection Beans created for each PersonDAO Bean
		logger.info("{}", personDAO1.jdbc_Connection);
		logger.info("{}", personDAO2.jdbc_Connection);
		// Output
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@5fe8b721
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@5fe8b721
		// Same Output - Singleton Scope Bean

	}
}

//*******************************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Singleton Scope Component
@Component
public class PersonDAO {  // DAO - Stands for Data Access Object.

    @Autowired
    JdbcConnection jdbc_Connection;  // Dependency Injection

    public PersonDAO(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }

    public JdbcConnection getJdbc_Connection() {
        return jdbc_Connection;
    }

    public void setJdbc_Connection(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }
}

//******************************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.stereotype.Component;

// Singleton Scope Component
@Component
public class JdbcConnection {

    public JdbcConnection() {
        System.out.println("JDBC Connection");
    }

}
```


# Case 2 : PersonDAO - Prototype Scope &&  JdbcConnection - Prototype Scope

```java
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

		// In this demostration, we have kept the scope of PersonDao Component as 'Prototype'. (New instance everytime)
		// And also we have set the scope of JdbcConnection component (which is Autowired in PersonDao Component) as 'Prototype'. (New instance everytime)
		// Concept Illustrated Below.

		// Creating a Bean 1 of PersonDao Component
		PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);

		// Creating a Bean 2 of PersonDao Component
		PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

		// Logging the PersonDAO Beans created
		logger.info("{}", personDAO1); // Same as sysout(personDAO1);
		logger.info("{}", personDAO2); // Same as sysout(personDAO1);
		// Output:
		// com.spring.basics.spring_basics.complexScope.PersonDAO@548e76f1 
		// com.spring.basics.spring_basics.complexScope.PersonDAO@3dd69f5a
		// Different Beans - Prototype Scope Bean

		// Logging the JdbcConnection Beans created for each PersonDAO Bean
		logger.info("{}", personDAO1.jdbc_Connection);
		logger.info("{}", personDAO2.jdbc_Connection);
		// Output
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@3aa3193a
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@1ee4730
		// Different Beans - Prototype Scope Bean
	}
}

//***********************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Prototype Scope Component
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersonDAO {  // DAO - Stands for Data Access Object.

    @Autowired
    JdbcConnection jdbc_Connection;  // Dependency Injection  // Prototype Scope Component

    public PersonDAO(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }

    public JdbcConnection getJdbc_Connection() {
        return jdbc_Connection;
    }

    public void setJdbc_Connection(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }
}

//***********************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Prototype Scope Component
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JdbcConnection {

    public JdbcConnection() {
        System.out.println("JDBC Connection");
    }
}
```

# Case 3 : PersonDAO - Singleton Scope &&  JdbcConnection - Prototype Scope

```java
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

		// In this demostration, we have kept the scope of PersonDao Component as 'Singleton'. (Only 1 instance for the entire SpringComplexScopeApplication)
		// And also we have set the scope of JdbcConnection component (which is Autowired in PersonDao Component) as 'Prototype'. (New instance everytime)
		// That means, we are basically asking the Spring framework to generate the same PersonDao Bean everytime which is carrying a different/new instance/bean of JdbcConnection component.
		// Now that is not possible.
		// So the Spring Framework treats the JdbcConnection component "also" as a Singleton scope component.
		// Concept Illustrated Below.

		// Creating a Bean 1 of PersonDao Component
		PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);

		// Creating a Bean 2 of PersonDao Component
		PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

		// Logging the PersonDAO Beans created
		logger.info("{}", personDAO1); // Same as sysout(personDAO1);
		logger.info("{}", personDAO2); // Same as sysout(personDAO1);
		// Output:
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2755d705 
		// com.spring.basics.spring_basics.complexScope.PersonDAO@2755d705
		// Same Output - Singleton Scope Bean

		// Logging the JdbcConnection Beans created for each PersonDAO Bean
		logger.info("{}", personDAO1.jdbc_Connection);
		logger.info("{}", personDAO2.jdbc_Connection);
		// Output
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@5fe8b721
		// com.spring.basics.spring_basics.complexScope.JdbcConnection@5fe8b721
		// Same Output - Singleton Scope Bean
		//  - Since the JdbcConnection component is autowired inside the 'PersonDAO' component - which is a 'singleton scope component' - therefore, even though we have set the scope of JdbcConnection component as "Prototype", the Spring framework treats it as a 'singleton scope component'.
	}
}

//**********************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Singleton Scope Component
@Component
public class PersonDAO {  // DAO - Stands for Data Access Object.

    @Autowired
    JdbcConnection jdbc_Connection;  // Dependency Injection  // Prototype Scope Component

    public PersonDAO(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }

    public JdbcConnection getJdbc_Connection() {
        return jdbc_Connection;
    }

    public void setJdbc_Connection(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }
}

//***********************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Prototype Scope Component
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JdbcConnection {

    public JdbcConnection() {
        System.out.println("JDBC Connection");
    }
}
```

# Case 4 : PersonDAO - Singleton Scope &&  JdbcConnection - Prototype Scope (proxyMode)

- ### In the last case,  even though we have set the scope of JdbcConnection component as "Prototype", the Spring framework treats it as a 'singleton scope component' - since the JdbcConnection component is autowired inside the 'PersonDAO' component - which is a 'singleton scope component'.

- ### But still, if we want to create a new/different bean of JdbcConnection component (Prototype Scope) - everytime we create a bean of 'PersonDAO' component (Singleton scope) - we need to use the annotation `@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)`

- See the illustration below:

```java
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

//************************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Singleton Scope Component
@Component
public class PersonDAO {  // DAO - Stands for Data Access Object.

    @Autowired
    JdbcConnection jdbc_Connection;  // Dependency Injection  // Prototype Scope Component

    public PersonDAO(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }

    public JdbcConnection getJdbc_Connection() {
        return jdbc_Connection;
    }

    public void setJdbc_Connection(JdbcConnection jdbc_Connection) {
        this.jdbc_Connection = jdbc_Connection;
    }
}

//************************************************************************************

package com.spring.basics.spring_basics.complexScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

// Prototype Scope Component
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS) 
public class JdbcConnection {

    public JdbcConnection() {
        System.out.println("JDBC Connection");
    }
}
```