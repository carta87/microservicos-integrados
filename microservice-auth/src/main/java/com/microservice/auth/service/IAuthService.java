package com.microservice.auth.service;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;

public interface IAuthService {

    AuthResponse login (LoginRequest loginRequest) ;

    AuthResponse register(RegisterRequest registerRequest);
}
