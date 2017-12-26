package com.webapi.newbie.auth;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.webapi.newbie.model.Account;
import com.webapi.newbie.model.AccountRole;
import com.webapi.newbie.model.JwtUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

    public static JwtUser create(Account account) {
        return new JwtUser(account.id, account.username, account.password, mapToGrantedAuthorities(account.roles),
                account.lastPasswordResetDate);
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(Set<AccountRole> accountRoles) {
        return accountRoles.stream().map(accountRole -> new SimpleGrantedAuthority(accountRole.role))
                .collect(Collectors.toSet());
    }

}