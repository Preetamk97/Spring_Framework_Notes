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
