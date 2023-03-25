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