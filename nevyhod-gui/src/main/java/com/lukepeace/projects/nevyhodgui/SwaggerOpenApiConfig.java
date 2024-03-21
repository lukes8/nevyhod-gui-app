package com.lukepeace.projects.nevyhodgui;

import com.lukepeace.projects.nevyhodcore.openapi.TagName;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Nevyhod REST API", description = "Open API v3", version = "v3"),
tags = {
        @Tag(name = TagName.ITEMS_CONTROLLER_FIREBASE)
})
public class SwaggerOpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI();
    }
}
