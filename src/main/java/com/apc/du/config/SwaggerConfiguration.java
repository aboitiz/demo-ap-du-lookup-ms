package com.apc.du.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class SwaggerConfiguration {

    public static final String DU = "DU-Lookup";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(Predicate.not(PathSelectors.regex("/error")))
                .paths(Predicate.not(PathSelectors.regex("/profile")))
                .build()
                .tags(new Tag(DU, "This page documents service inquiry RESTful Service Endpoints"));
    }


}
