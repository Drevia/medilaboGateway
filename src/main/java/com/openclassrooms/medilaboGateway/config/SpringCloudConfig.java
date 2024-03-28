package com.openclassrooms.medilaboGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/patient/**")
                        /*.filters(f -> f.prefixPath("/api"))*/
                        .uri("http://localhost:8091/"))

                .route(r -> r.path("/consumer/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
}
