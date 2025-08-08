package com.azecoders.rrbank.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentationConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Banking app")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Riyad Rahimov")
                        .email("riyad_rahimov.dev@mail.ru")
                ));
    }
}