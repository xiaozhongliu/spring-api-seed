package com.webapi.newbie.auth;

import java.util.List;

import com.webapi.newbie.entity.Account;
import com.webapi.newbie.entity.AccountRole;
import com.webapi.newbie.service.IAccountRoleService;
import com.webapi.newbie.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountRoleService accountRoleService;

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