package com.webapi.newbie.auth;

import java.io.Serializable;

public class JwtAuthResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }
}