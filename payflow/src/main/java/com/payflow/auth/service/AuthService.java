package com.payflow.auth.service;

import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}