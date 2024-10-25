package dev.abraham.ecommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public OpenAPI customerServiceAPI() {
        return new OpenAPI().info(new Info().title("Customer Service")
                .description("Customer Service API")
                .version("1.0")
                .license(new License().name("Apache 2.0"))
        ).externalDocs(new ExternalDocumentation().description("Customer Service Documentation")
                .url("https://www.apache.org/licenses/LICENSE-2.0"));
    }
}
