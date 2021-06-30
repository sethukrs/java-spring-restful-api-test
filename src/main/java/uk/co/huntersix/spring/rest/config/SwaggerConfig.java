package uk.co.huntersix.spring.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Config class for swagger
 * @author sraamasubbu
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(PathSelectors.regex("/.*"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "Spring restful api test",
            "Service where persons can be added and retrieved",
            "1.0",
            "Terms of service",
            "",
            "License of API",
            "https://swagger.io/docs/");
        return apiInfo;
    }

}
