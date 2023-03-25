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
