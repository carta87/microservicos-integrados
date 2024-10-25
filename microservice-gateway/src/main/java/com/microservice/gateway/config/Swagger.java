package com.microservice.gateway.config;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

@Configuration
public class Swagger {

    @Bean
    @Lazy(false)
    public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiConfigParameters) {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-service", "");
            AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, DEFAULT_API_DOCS_URL, null);
            urls.add(swaggerUrl);
        });
        swaggerUiConfigParameters.setUrls(urls);
        return urls;
    }
}
