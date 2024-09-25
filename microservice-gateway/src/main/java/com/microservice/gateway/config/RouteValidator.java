package com.microservice.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/eureka",
            "/auth-micro"//"/auth/",
            //"/course-micro", Cuando las utilizamos no aplica JWT
            //"/student-micro", por lo cual se realiza validacion en AuthenticationFilter del swagger
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
