package com.microservice.gateway.config;

import com.microservice.gateway.exception.exception.JwtException;
import com.microservice.gateway.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            // Ignorar la validación de JWT para las rutas de Swagger
            if (path.contains("/v3/api-docs") ||
                    path.contains("/swagger-ui") ||
                    path.contains("/swagger-resources")) {
                return chain.filter(exchange);
            }

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new JwtException("falta encabezado de autorización", HttpStatus.FORBIDDEN);
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                     jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    log.info("invalid access...!");
                    throw new JwtException("no autorizado al validar jwt", HttpStatus.FORBIDDEN);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}

