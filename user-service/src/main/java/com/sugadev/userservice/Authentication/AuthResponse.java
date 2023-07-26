package com.sugadev.userservice.Authentication;

import lombok.Data;

@Data
public class AuthResponse {
    private String username;
    private String accessToken;
    public AuthResponse() { }
    public AuthResponse(String email, String accessToken) {
        this.username = email;
        this.accessToken = accessToken;
    }
}
