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
