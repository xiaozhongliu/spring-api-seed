package com.webapi.newbie.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}