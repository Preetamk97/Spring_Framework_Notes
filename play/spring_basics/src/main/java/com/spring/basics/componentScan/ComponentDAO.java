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