package com.webapi.seed.auth;

import com.webapi.seed.domain.JwtUser;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.AccountRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(Account account, List<AccountRole> roles) {
        return new JwtUser(
                account.getAccountId(),
                account.getUsername(),
                account.getPassword(),
                mapToGrantedAuthorities(roles),
                account.getLastPasswordResetDate()
        );
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(List<AccountRole> accountRoles) {
        return accountRoles.stream()
                .map(accountRole -> new SimpleGrantedAuthority(accountRole.getRole()))
                .collect(Collectors.toSet());
    }

}