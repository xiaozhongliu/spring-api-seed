package com.webapi.seed.controller;

import javax.servlet.http.HttpServletRequest;

import com.webapi.seed.auth.IAuthService;
import com.webapi.seed.entity.Account;
import com.webapi.seed.domain.AuthResponse;
import com.webapi.seed.domain.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private IAuthService authService;

    @PostMapping(path = "${jwt.route.auth.register}")
    public ResponseEntity<?> register(@RequestBody Account account) throws AuthenticationException {
        boolean result = authService.register(account);
        if (!result) {
            return ResponseEntity.badRequest().body("account already exists");
        }
        return ResponseEntity.ok(new Result(1, "success"));
    }

    @PostMapping(path = "${jwt.route.auth.path}")
    @ResponseBody
    public Result login(@RequestBody Account account) throws AuthenticationException {
        String token = authService.login(account.username, account.password);
        return new Result(1, "success", new AuthResponse(token));
    }

    @GetMapping(path = "${jwt.route.auth.refresh}")
    public ResponseEntity<?> refreshAndGetAuthToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body("no need to refresh");
        }
        return ResponseEntity.ok(new Result(1, "success", new AuthResponse(refreshedToken)));
    }

}
