package com.myapi.peoplews.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stefanini.selecao.peoplews"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .securitySchemes(Arrays.asList(securityScheme()))
	            .securityContexts(Arrays.asList(securityContexts()));
               
                
    }
	
	
	@SuppressWarnings("deprecation")
	private SecurityContext securityContexts() {
	    return SecurityContext.builder()
	            .securityReferences(Arrays.asList(basicAuthReference()))
	            .forPaths(PathSelectors.any())	            
	            .build();
	}

	private SecurityScheme securityScheme() {
	    return new BasicAuth("basicAuth");
	}

	private SecurityReference basicAuthReference() {
	    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}
}
