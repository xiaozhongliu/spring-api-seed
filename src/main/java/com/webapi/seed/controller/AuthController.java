package com.webapi.seed.controller;

import com.webapi.seed.auth.IAuthService;
import com.webapi.seed.config.JwtProps;
import com.webapi.seed.domain.AuthResponse;
import com.webapi.seed.domain.Result;
import com.webapi.seed.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtProps jwtProps;

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
        String token = request.getHeader(jwtProps.getHeader());
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body("no need to refresh");
        }
        return ResponseEntity.ok(new Result(1, "success", new AuthResponse(refreshedToken)));
    }

}
