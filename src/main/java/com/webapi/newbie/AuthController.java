package com.webapi.newbie;

import javax.servlet.http.HttpServletRequest;

import com.webapi.newbie.auth.AuthService;
import com.webapi.newbie.auth.JwtAuthResponse;
import com.webapi.newbie.model.Account;
import com.webapi.newbie.model.Result;

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

@Controller
@RequestMapping(path = "/")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "${jwt.route.auth.register}")
    public ResponseEntity<?> register(@RequestBody Account account) throws AuthenticationException {
        Account result = authService.register(account);
        if (result == null) {
            return ResponseEntity.badRequest().body("account already exists");
        }
        return ResponseEntity.ok(new Result(1, "success", result));
    }

    @PostMapping(path = "${jwt.route.auth.path}")
    @ResponseBody
    public Result login(@RequestBody Account account) throws AuthenticationException {
        String token = authService.login(account.username, account.password);
        return new Result(1, "success", new JwtAuthResponse(token));
    }

    @GetMapping(path = "${jwt.route.auth.refresh}")
    public ResponseEntity<?> refreshAndGetAuthToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body("no need to refresh");
        }
        return ResponseEntity.ok(new Result(1, "success", new JwtAuthResponse(refreshedToken)));
    }

}