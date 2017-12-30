package com.webapi.newbie.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.webapi.newbie.entity.Account;
import com.webapi.newbie.entity.AccountRole;
import com.webapi.newbie.model.JwtUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

    public static JwtUser create(Account account, List<AccountRole> roles) {
        return new JwtUser(account.id, account.username, account.password, mapToGrantedAuthorities(roles),
                account.lastPasswordResetDate);
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(List<AccountRole> accountRoles) {
        return accountRoles.stream().map(accountRole -> new SimpleGrantedAuthority(accountRole.role))
                .collect(Collectors.toSet());
    }

}