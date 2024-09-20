package com.microservice.auth.controller;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.service.implementacion.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CompromisedPasswordChecker passwordChecker;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public  ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        CompromisedPasswordDecision decision =passwordChecker.check(registerRequest.getPassword());
        if (decision.isCompromised()) {
            throw new IllegalArgumentException("Contraseña  muy utilizada.... cambiarla");
        }
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
