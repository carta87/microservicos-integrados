/*package com.microservice.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CorsFilter extends AbstractGatewayFilterFactory<CorsFilter.Config> {

    public CorsFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Manejo de solicitudes OPTIONS
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethod().name())) {
                System.out.println("Manejando solicitud OPTIONS");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Accept, Content-Type, Authorization");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                return exchange.getResponse().setComplete();
            }

            // Para otros métodos
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");
            }));
        };
    }

    public static class Config {
        // Configuración adicional si es necesario
    }
}
*/