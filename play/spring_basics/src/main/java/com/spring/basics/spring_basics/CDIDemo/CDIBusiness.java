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
