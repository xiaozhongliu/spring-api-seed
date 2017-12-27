package com.webapi.newbie.auth;

import java.util.Date;

import com.webapi.newbie.model.Account;
import com.webapi.newbie.model.AccountRole;
import com.webapi.newbie.model.JwtUser;
import com.webapi.newbie.repo.AccountRepo;
import com.webapi.newbie.repo.AccountRoleRepo;

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

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private AccountRepo accountRepo;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil, AccountRepo accountRepo) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountRepo = accountRepo;
    }

    @Override
    public Account register(Account account) {
        if (accountRepo.findByUsername(account.username).isPresent()) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.password = encoder.encode(account.password);
        account.lastPasswordResetDate = new Date();

        AccountRole accountRole = new AccountRole(account, "USER");
        account.roles.add(accountRole);

        Account savedAccount = accountRepo.save(account);

        return savedAccount;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication auth = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
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