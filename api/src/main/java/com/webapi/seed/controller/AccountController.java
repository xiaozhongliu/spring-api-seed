package com.webapi.seed.controller;

import com.webapi.seed.controller.base.BaseController;
import com.webapi.seed.domain.Result;
import com.webapi.seed.entity.Account;
import com.webapi.seed.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    @ResponseBody
    public ResponseEntity getAccountList() {
        Iterable<Account> accounts = accountService.selectAll();
        return Result.Ok(accounts);
    }

    @GetMapping(path = "/{username}")
    @ResponseBody
    public ResponseEntity getAccount(@PathVariable String username) {
        Account account = accountService.selectByUsername(username);
        return Result.Ok(account);
    }

}
