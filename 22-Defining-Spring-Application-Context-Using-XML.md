Before **Spring 2.5**, ***annotations*** were **not used** for ***Bean and Dependency Management*** and all the components had to be configured directly through the **pom.xml** file.

In this lesson, we wil see how we can configure beans and manage dependecies from the **pom.xml** file directly.

1. Create a new file **applicationContext.xml** inside the **resources** folder of the project.

![](imgfiles\chap22\2023-03-15-11-47-39.png)

1. Google **spring documentation application context xml**.
1. Click on the first link **5. The IoC container - Spring**.
1. Copy the following boiler plate code and paste it to the **applicationContext.xml** file.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="..." class="...">
    <!-- collaborators and configuration for this bean go here -->
  </bean>

  <bean id="..." class="...">
    <!-- collaborators and configuration for this bean go here -->
  </bean>

  <!-- more bean definitions go here -->

</beans>
```

1. Create a new package **XMLContext** and and add the following classes to it. Notice that in the code below, we have not used the previously used `@Component` and `@Autowired` annotations anywhere.

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

//****************************************************************************

package com.spring.basics.spring_basics.XMLContext;

public class XmlJdbcConnection {

    public XmlJdbcConnection() {
        System.out.println("XML JDBC Connection");
    }
}
```

1. Creating beans for the **XmlJdbcConnection.java** & **XmlDAO.java** classes and autowiring the **XmlJdbcConnection** dependency to the **XmlDAO.java** class in the **applicationContext.xml** file.

![](imgfiles\chap22\2023-03-15-12-20-27.png)

```xml
<!-- Creating bean for XmlJdbcConnection.java class -->
<bean id="xmlJdbcConnection" class="com.spring.basics.spring_basics.XMLContext.XmlJdbcConnection">
</bean>

<!-- Creating bean for XmlDAO.java class -->
<bean id="xmlDAO" class="com.spring.basics.spring_basics.XMLContext.XmlDAO">
    <!-- Autowiring XmlJdbcConnection.java dependency inside XmlDAO.java -->
    <property name="xmlJdbcConnection" ref="xmlJdbcConnection"/>
<!-- 'ref' attribute is the id of the XmlJdbcConnection bean -->
</bean>
```

1. **Spring Application code** for this lesson is as follows:

```java
package com.spring.basics.spring_basics.XMLContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringXmlApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        // When we use the 'applicationContext.xml' file (which is created and stored inside
        // the 'resources' folder of our project) for configuring and managing our beans and
        // dependencies - instead of using Annotations for the same, 
        // we use the 'ClassPathXmlApplicationContext' class instead of
        // the default 'ApplicationContext' class for creating the 'applicationContext'
        // object of our Application class.
        // Also, if we place our "applicationContext.xml" - anywhere else other than the resources folder
        // we need to give the complete address of the file as argument while creating the 
        // 'ClassPathXmlApplicationContext' object above.

        XmlDAO xmlDAO = applicationContext.getBean(XmlDAO.class);
        System.out.println(xmlDAO);
        System.out.println(xmlDAO.getXmlJdbcConnection());

        applicationContext.close();
    }
}
```

## Output:

![](imgfiles\chap22\2023-03-15-15-28-36.png)