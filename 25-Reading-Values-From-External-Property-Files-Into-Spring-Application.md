In this lesson, we are gonna learn how to use values from an ***External Propertise File*** in our Spring Application.

- Firstly, create a file named **app.propertise** inside the resources folder. <br>

![](imgfiles\chap25\2023-03-16-11-43-02.png)

- Add the following line to the **app.propertise** file: `external.service.url = http://someserver.dev.com/service`

- Crretae a new package named **externalPropertise**. <br>

![](imgfiles\chap25\2023-03-16-11-48-14.png)

- Inside **externalPropertise** package, create a class named **ExternalService.java** and add the following code to it.

```java
package com.spring.basics.spring_basics.externalPropertise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalService {

    @Value("${external.service.url}")
    private String url;
    // we will read this value (url) from an external propertise file
    // (app.propertise) present inside the resources folder.
    // external.service.url = htp://someserver.dev.com/service

    public String returnServiceUrl() {
        return url;
    }
}
```

- Inside **externalPropertise** package, create another class named **SpringExternalPropertiseApplication.java** and add the following code to it.

```java
package com.spring.basics.spring_basics.externalPropertise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.propertise")  // We will have to add this annotation here because, the 'ExternalService' bean - used below - is accessing a value from this 'app.propertise' file.
public class SpringExternalPropertiseApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(SpringExternalPropertiseApplication.class, args);

        ExternalService externalService = applicationContext.getBean(ExternalService.class);

        String returnedUrl = externalService.returnServiceUrl();

        System.out.println(returnedUrl);
        // http://someserver.dev.com/service
    }
    
}
```