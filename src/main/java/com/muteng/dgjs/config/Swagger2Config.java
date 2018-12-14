package com.muteng.dgjs.config;

import io.swagger.annotations.Api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan("com.muteng.dgjs.api")
public class Swagger2Config {

	@Bean
    public Docket createAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("dgjs")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder().title("打工集市接口展示").description("Interface API").version("1.0").build();

        return apiInfo;
    }
    
}
