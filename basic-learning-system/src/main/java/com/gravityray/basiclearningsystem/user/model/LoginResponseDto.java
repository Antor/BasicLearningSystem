package com.gravityray.basiclearningsystem.user.model;

@SuppressWarnings("unused")
public class LoginResponseDto {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
