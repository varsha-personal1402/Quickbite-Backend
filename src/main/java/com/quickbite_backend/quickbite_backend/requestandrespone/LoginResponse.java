package com.quickbite_backend.quickbite_backend.requestandrespone;

public class LoginResponse {
    private String message;
    private boolean success;

    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // getters
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}
