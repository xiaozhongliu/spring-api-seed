package com.webapi.newbie.auth;

import java.io.Serializable;

public class JwtAuthResponse implements Serializable {

    public String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }
}