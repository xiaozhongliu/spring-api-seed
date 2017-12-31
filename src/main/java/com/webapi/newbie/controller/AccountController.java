package com.webapi.newbie.controller;

import com.webapi.newbie.domain.Result;
import com.webapi.newbie.entity.Account;
import com.webapi.newbie.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    @ResponseBody
    public Result getAccountList() {
        Iterable<Account> accounts = accountService.selectAll();
        return new Result(1, "success", accounts);
    }

    @GetMapping(path = "/{username}")
    @ResponseBody
    public Result getAccount(@PathVariable String username) {
        Account account = accountService.selectByUsername(username);
        return new Result(1, "success", account);
    }

}
