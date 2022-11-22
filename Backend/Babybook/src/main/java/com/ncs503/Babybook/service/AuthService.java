package com.ncs503.Babybook.service;

import com.ncs503.Babybook.models.request.LoginRequest;
import com.ncs503.Babybook.models.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    LoginResponse login(LoginRequest request);
}
