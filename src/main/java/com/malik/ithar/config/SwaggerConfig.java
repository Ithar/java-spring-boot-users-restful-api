package com.malik.ithar.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        Contact contact = new Contact(
            "Ithar Malik",
            "http://ithar.malik.com",
            "ithar.malik@gmail.com");

        return new ApiInfo(
                "Users RESTful API",
                "Basic RESTful API functions GET/POST/PUT/DELETE",
                "1.0",
                "Free to use",
                contact,
                "This is free and unencumbered software released into the public domain ",
                "http://unlicense.org",
                new ArrayList<>());
    }

}
