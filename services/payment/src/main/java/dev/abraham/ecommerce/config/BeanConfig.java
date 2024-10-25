package dev.abraham.ecommerce.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class BeanConfig {
    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-topic").build();
    }

    @Bean
    public OpenAPI paymentServiceAPI() {
        return new OpenAPI().info(new Info().title("Payment Service")
                .description("Payment Service API")
                .version("1.0")
                .license(new License().name("Apache 2.0"))
        ).externalDocs(new ExternalDocumentation().description("Payment Service Documentation")
                .url("https://www.apache.org/licenses/LICENSE-2.0"));
    }
}
