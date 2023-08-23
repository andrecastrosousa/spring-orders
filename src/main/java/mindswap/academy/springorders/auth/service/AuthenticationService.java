package mindswap.academy.springorders.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mindswap.academy.springorders.auth.dto.AuthenticationRequest;
import mindswap.academy.springorders.auth.dto.AuthenticationResponse;
import mindswap.academy.springorders.auth.dto.RegisterRequest;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
