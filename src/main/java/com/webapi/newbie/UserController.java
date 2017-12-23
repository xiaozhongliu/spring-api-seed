package com.webapi.newbie;

import com.webapi.newbie.model.Account;
import com.webapi.newbie.model.Result;
import com.webapi.newbie.repo.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "users")
public class UserController {

    private final AccountRepo accountRepo;

    @Autowired
    UserController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public @ResponseBody Result getAccountList() {
        Iterable<Account> accounts = accountRepo.findAll();
        return new Result(1, "success", accounts);
    }

    @GetMapping(path = "/{username}")
    public @ResponseBody Result getAccount(@PathVariable String username) {
        Object account = accountRepo.findByUsername(username).orElse(null);
        return new Result(1, "success", account);
    }

}