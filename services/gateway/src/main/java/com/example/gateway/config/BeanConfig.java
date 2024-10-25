package com.example.gateway.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public OpenAPI apiGatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway")
                        .description("API Gateway for managing requests to multiple services")
                        .version("1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Product Service Documentation")
                        .url("http://localhost:8050/swagger-ui/index.html"));
    }
}
