package com.bookstore.Bookstore.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurartion {

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi
                .builder()
                .group("Api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Book Store Api")
                                .description("Book store REST API.")
                                .version("1.0"));
    }
}
