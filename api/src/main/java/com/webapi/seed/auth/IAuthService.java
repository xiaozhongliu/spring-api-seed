package com.webapi.seed.auth;

import com.webapi.seed.entity.Account;

public interface IAuthService {

    boolean register(Account account);

    String login(String username, String password);

    String refresh(String token);

}