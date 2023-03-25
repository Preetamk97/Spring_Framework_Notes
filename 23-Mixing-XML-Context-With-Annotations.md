 
# applicationContext.xml :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"  
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- This line of code will scan the entire package of 'com.spring.basics.spring_basics' and load all the components created within this package inside the applicationContext.xml -->
    <!-- So whichever Spring Application uses this applicationContext.xml to get its beans , can access all the beans available under 'com.spring.basics.spring_basics' package - apart from the ones created inside this 'applicationContext.xml' file. -->
    <context:component-scan base-package="com.spring.basics.spring_basics"></context:component-scan>

    <!-- Creating bean for XmlJdbcConnection.java class -->
    <bean id="xmlJdbcConnection" class="com.spring.basics.spring_basics.XMLContext.XmlJdbcConnection">
    </bean>

    <!-- Creating bean for XmlDAO.java class -->
    <bean id="xmlDAO" class="com.spring.basics.spring_basics.XMLContext.XmlDAO">
        <!-- Autowiring XmlJdbcConnection.java dependency inside XmlDAO.java -->
        <property name="xmlJdbcConnection" ref="xmlJdbcConnection"/>
        <!-- 'ref' attribute is the id of the XmlJdbcConnection bean -->
    </bean>

</beans>
```

# SpringXmlApplication.java

```java
package com.spring.basics.spring_basics.XMLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringXmlApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(SpringXmlApplication.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        // When we use the 'applicationContext.xml' file (which is created and stored
        // inside the 'resources' folder of our project) for configuring and managing
        // our beans
        // and dependencies - instead of using Annotations for the same,
        // we use the 'ClassPathXmlApplicationContext' class instead of
        // the default 'ApplicationContext' class for creating the 'applicationContext'
        // object of our Application class.
        // Also, if we place our "applicationContext.xml" - anywhere else other than the
        // resources folder we need to give the complete address of the file as argument
        // while creating the 'ClassPathXmlApplicationContext' object above.

        // At this point, the spring application already knows about all the beans that
        // are known to/recorded in the 'applicationContext.xml' file.
        // We can check this using getBeanDefinitionNames() function on
        // applicationContext object.
        // Note that we havent requested for any beans for use in this application at
        // this stage yet.
        LOGGER.info("Beans Loaded -> {}", (Object) applicationContext.getBeanDefinitionNames());
        // info() methods only accepts a single variable as its argument.
        // 'applicationContext.getBeanDefinitionNames()' returns us an array of Bean
        // definitions.
        // When we pass this array to .info() method, without typecasting it as an
        // (Object), it only takes the first element of the array and replaces it in
        // place of {}.
        // Therefore, to log the entire of array, we need to pass it by typecasting it
        // to (Object).
        // If you remove the (Object) typecasting from the above line of code, VS Code
        // also shows you an yellow squiggle line below it - saying that you should
        // typecast the 'applicationContext.getBeanDefinitionNames()' to (Object).

        // Under normal conditions, the above line of code will list only those beans
        // that are listed for creation in 'applicationContext.xml' file under <beans>
        // tag.
        // Old Output->> [xmlJdbcConnection, xmlDAO].
        // But we can also configure our 'applicationContext.xml' file for scanning all
        // classes/components of other packages also (ComponentScan).
        // To do this, do the following additions in the <beans ...> tag of the
        // 'applicationContext.xml' file:
        // <beans xmlns="http://www.springframework.org/schema/beans"
        // xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        // xmlns:context="http://www.springframework.org/schema/context" <<------
        // xsi:schemaLocation="http://www.springframework.org/schema/beans
        // http://www.springframework.org/schema/beans/spring-beans.xsd
        // http://www.springframework.org/schema/context <<-------
        // http://www.springframework.org/schema/context/spring-context.xsd" <<-------
        // >
        // The above additions enables the 'applicationContext.xml' file to perform
        // Component-Scan on packages and find all the components and dependencies
        // created in them.
        // Finally add the following line of code to the 'applicationContext.xml' file:
        // <context:component-scan
        // base-package="com.spring.basics.spring_basics"></context:component-scan>
        // This line of code will scan the entire package of
        // 'com.spring.basics.spring_basics' and load all the beans that were ever
        // created (using annotations as well as xml context) within this package inside
        // the applicationContext.xml - for our access.
        // So whichever Spring Application uses this applicationContext.xml to get its
        // beans can access all the beans available under
        // 'com.spring.basics.spring_basics' package.
        // Also, it does not matter, whether the beans were created using anotations or
        // xml context.
        // New Output ->> [binarySearchImpl, bubbleSortAlgorithm, quickSortAlgorithm,
        // springBasicApplication, springCDIDemoApplication,
        // scopedTarget.jdbcConnection, jdbcConnection, personDAO,
        // springComplexScopeApplication, springComponentScanApplication,
        // springXmlApplication,
        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor,
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor,
        // org.springframework.context.annotation.internalCommonAnnotationProcessor,
        // org.springframework.context.event.internalEventListenerProcessor,
        // org.springframework.context.event.internalEventListenerFactory,
        // xmlJdbcConnection, xmlDAO, componentDAO, componentJdbcConnection,
        // org.springframework.boot.autoconfigure.AutoConfigurationPackages,
        // org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,
        // propertySourcesPlaceholderConfigurer,
        // org.springframework.boot.autoconfigure.aop.AopAutoConfiguration$ClassProxyingConfiguration,
        // forceAutoProxyCreatorToUseClassProxying,
        // org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,
        // org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration,
        // applicationAvailability,
        // org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,
        // org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor,
        // org.springframework.boot.context.internalConfigurationPropertiesBinder,
        // org.springframework.boot.context.properties.BoundConfigurationProperties,
        // org.springframework.boot.context.properties.EnableConfigurationPropertiesRegistrar.methodValidationExcludeFilter,
        // org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration,
        // lifecycleProcessor,
        // spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties,
        // org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,
        // spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties,
        // org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration,
        // spring.sql.init-org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties,org.springframework.boot.sql.init.dependency.DatabaseInitializationDependencyConfigurer$DependsOnDatabaseInitializationPostProcessor,
        // org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,
        // taskExecutorBuilder, applicationTaskExecutor,
        // spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties,
        // org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,
        // taskSchedulerBuilder,
        // spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties,
        // org.springframework.aop.config.internalAutoProxyCreator]

        XmlDAO xmlDAO = applicationContext.getBean(XmlDAO.class);
        LOGGER.info("{}", xmlDAO);
        LOGGER.info("{}", xmlDAO.getXmlJdbcConnection());
        applicationContext.close();
    }
}
```

# XmlDAO.java

```java
package com.spring.basics.spring_basics.XMLContext;

public class XmlDAO { 
    XmlJdbcConnection xmlJdbcConnection; // Dependency

    // Do not use constructor in this case. Or else you will get the "Default constructor not found error."
    // INstead use getters and setters for each of the dependencies.   

    public XmlJdbcConnection getXmlJdbcConnection() {
        return xmlJdbcConnection;
    }

    public void setXmlJdbcConnection(XmlJdbcConnection xmlJdbcConnection) {
        this.xmlJdbcConnection = xmlJdbcConnection;
    }
}
```

# XmlJdbcConnection.java

```java
package com.spring.basics.spring_basics.XMLContext;

public class XmlJdbcConnection {

    public XmlJdbcConnection() {
        System.out.println("XML JDBC Connection");
    }
}
``` 