package com.webapi.seed.auth;

import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.AccountRole;
import com.webapi.seed.service.AccountRoleService;
import com.webapi.seed.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRoleService accountRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.selectByUsername(username);
        List<AccountRole> roles = accountRoleService.selectByUsername(username);

        if (account != null) {
            return JwtUserFactory.create(account, roles);
        }

        throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    }
}