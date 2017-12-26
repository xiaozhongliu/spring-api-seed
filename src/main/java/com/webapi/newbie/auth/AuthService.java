package com.webapi.newbie.auth;

import com.webapi.newbie.model.Account;

public interface AuthService {

    Account register(Account account);

    String login(String username, String password);

    String refresh(String token);

}