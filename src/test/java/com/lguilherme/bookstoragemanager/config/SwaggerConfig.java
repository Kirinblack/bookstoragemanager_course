package com.lguilherme.bookstoragemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String BASE_PACKAGE = "com.lguilherme.bookstoragemanager";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new  ApiInfoBuilder()
                .title("Booksstore Manager Course")
                .description("Bookstorage Manager API Professional")
                .version("1.0.0")
                .contact(new Contact("Luiz Guilherme","https://github.com/Kirinblack","masterextreme074@gmsil.com" ))
                .build();
    }
}


