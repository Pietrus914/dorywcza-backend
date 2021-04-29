package com.example.dorywcza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        String description = "Welcome!\n" +
                "DORYWCZA API allows you to browse and fetch offers' and users' details stored in dorywcza.pl system.\n" +
                "Data are provided in JSON format via REST api. Below you can find helpful instructions how to use API to get data from resources.\n" +
                "Enjoy!";

        return new ApiInfoBuilder()
                .title("Dorywcza.pl API")
                .description(description)
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("Admin", "http://localhost:8080/dorywcza/docs", "admin@dorywcza.pl"))
                .build();
    }
}
