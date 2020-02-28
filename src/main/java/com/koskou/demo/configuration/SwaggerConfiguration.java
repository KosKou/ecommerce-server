package com.koskou.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/*
* FLOW: Expose/Controller -> Business/Service -> DAO/Repository -> PROXY API/DB
* */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Contact DEFAULT_CONTACT = new Contact(
            "Banner Gonzales","http://www.google.com","egonzalz@everis.com"
    );

    private static final Collection<VendorExtension> DEFAULT_VENDOR = new ArrayList<>();
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Hey What's Up","Everis Documentation", "1.0",
            "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.google.com.pe", DEFAULT_VENDOR);
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO);
    }
}
