package com.webapi.newbie.auth;

import com.webapi.newbie.entity.Account;

public interface AuthService {

    boolean register(Account account);

    String login(String username, String password);

    String refresh(String token);

}