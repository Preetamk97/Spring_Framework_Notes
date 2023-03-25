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
