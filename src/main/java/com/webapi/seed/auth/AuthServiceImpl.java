package com.webapi.seed.auth;

import com.webapi.seed.domain.JwtUser;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.AccountRole;
import com.webapi.seed.service.IAccountRoleService;
import com.webapi.seed.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements IAuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private IAccountService accountService;
    private IAccountRoleService accountRoleService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            IAccountService accountService,
            IAccountRoleService accountRoleService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.accountRoleService = accountRoleService;
    }

    @Override
    public boolean register(Account account) {
        if (accountService.selectByUsername(account.username) != null) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.password = encoder.encode(account.password);
        account.lastPasswordResetDate = new Date();

        AccountRole accountRole = new AccountRole(account.id, "ROLE_USER");

        boolean accountResult = accountService.insert(account);
        boolean accountRoleResult = accountRoleService.insert(accountRole);

        return accountResult && accountRoleResult;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication auth = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

}