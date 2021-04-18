package com.example.homeproject.security;

public interface JwtSecurityService {
    void saveJwtToken(String token);
    String getToken();
    void deleteToken();
}

