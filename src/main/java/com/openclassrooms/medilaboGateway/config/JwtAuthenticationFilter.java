package com.openclassrooms.medilaboGateway.config;

import com.openclassrooms.medilaboGateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtService service;

    private final Logger LOG = Logger.getLogger(JwtAuthenticationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getPrincipal().map(principale -> exchange.mutate().request(request ->
                request.headers(headers -> headers.setBearerAuth(service.generateToken(principale)))).build())
                .flatMap(chain::filter).doOnError(error -> LOG.warn("Could not filter the request"));
    }
}
