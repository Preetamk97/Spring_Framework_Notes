- **CDI** ***(Context and Dependency Injection)*** is a **standard dependency injection framework/API** included in Java EE 6 and higher.
- Anything you can do with **CDI** is not new and you can have those stuffs in **Spring Framework** as well.
- To use the **CDI** API in your Spring project, you need to add it as a **dependency** in the ***pom.xml*** file of the Spring project.
- ***@Named*** annotation of the **CDI API** - works same as ***@Component*** annotation of the Spring framework.
- ***@Inject*** annotation of the **CDI API** - works same as ***@Autowired*** annotation of the Spring framework.
- ***@Singleton*** annotation of the **CDI API** - Defines the **Singleton** scope of a Component.
- ***@Scope*** annotation of the **CDI API** - Works same as **@Scope** annotation of the Spring framework.
- ***@Qualifier*** annotation of the **CDI API** - Works same as **@Qualifier** annotation of the Spring framework.

# Adding CDI Dependency to the Spring Project:

- Open your **pom.xml** file and add the following **dependency code** inside the <dependencies> tag.

    ```xml
    <!-- Adding CDI Depedency  -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
    ```

    After adding the above code, you can see the following **jar** file has been added inside the **Maven Dependencies**.

    ![](imgfiles\chap20\2023-03-14-15-22-24.png) 

    ![](imgfiles\chap20\2023-03-14-15-24-59.png)

    On expanding the **jar** file, inside **JAVA PROJECTS**, we can see the following interfaces - **Inject, Named, Provider, Qualifier, Scope and Singleton**.

# CDI Annotations - @Named & @Inject - Use Demo:

```java
package com.spring.basics.spring_basics.CDIDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringCDIDemoApplication {

    // Creating Logger Object
    private static Logger LOGGER = LoggerFactory.getLogger(SpringCDIDemoApplication.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(SpringCDIDemoApplication.class, args);

        // Creating a Bean of CDIBusiness Component
        CDIBusiness cdiBusiness = applicationContext.getBean(CDIBusiness.class);

        // Logging the name of the 'CDIBusiness' bean that is being created + the name of the 'CDIDAO' dependency bean that is being created inside the 'CDIBusiness' bean. 
        LOGGER.info("{} dao-{}", cdiBusiness, cdiBusiness.getCdidao());
        // com.spring.basics.spring_basics.CDIDemo.CDIBusiness@2e77b8cf dao-com.spring.basics.spring_basics.CDIDemo.CDIDAO@2c4ca0f9

    }
}

//***********************************************************************************************

package com.spring.basics.spring_basics.CDIDemo;

import javax.inject.Inject;
import javax.inject.Named;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// @Component
@Named  // CDI Alternative to @Component
public class CDIBusiness {
    // Autowiring // Dependency Injection
    // @Autowired
    @Inject  // CDI Alternative to @Autowired
    CDIDAO cdidao;

    public CDIBusiness(CDIDAO cdidao) {
        this.cdidao = cdidao;
    }

    public CDIDAO getCdidao() {
        return cdidao;
    }

    public void setCdidao(CDIDAO cdidao) {
        this.cdidao = cdidao;
    }
}

//*******************************************************************************************

package com.spring.basics.spring_basics.CDIDemo;

import javax.inject.Named;

// import org.springframework.stereotype.Component;

// @Component
@Named  // CDI Alternative to @Component
public class CDIDAO {
    
}
```