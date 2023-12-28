package com.product.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.product.rest"))
				.paths(PathSelectors.regex("/api.*")) // . = one char , * = many
				.build()
				.apiInfo(apiInfo());
	}

	//meta data (Just additional information shown at starting of swagger UI
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"KARTHIK PRODUCT APP",//title
				 "SAMPLE DESC",//description
				 "R3.2",//version
				 "https://nareshit.in/", //service URL
				 new Contact("KARTHIK","https://nareshit.in/" , "iamkarthikkuruva@gmail.com"),
				 "NIT LICENSE", //licence provider
				 "https://nareshit.in/", 
				 List.of());
	}
}