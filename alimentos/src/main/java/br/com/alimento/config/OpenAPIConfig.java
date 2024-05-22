package br.com.alimento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class OpenAPIConfig {
	
@Bean
   private OpenAPI customOpenAPI() {
	   return new OpenAPI()
			   .info(new Info()
			   .title("Alimentos API com Java e Spring Boot")
			   .version("v1")
			   .description("Alimentos Web API")
			   .termsOfService("https://youtube.com")
			   .license(new License().name("Apache 2.0")
					   .url("https://youtube.com")));
   }

}
