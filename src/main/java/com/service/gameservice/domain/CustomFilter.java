package com.service.gameservice.domain;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomFilter extends ResourceConfig {

    public CustomFilter()
    {
        packages("com.service.gameservice");

        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}
