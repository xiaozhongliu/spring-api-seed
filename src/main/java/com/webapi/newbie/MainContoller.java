package com.webapi.newbie;

import com.webapi.newbie.model.Result;
import com.webapi.newbie.model.User;
import com.webapi.newbie.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/user")
public class MainContoller {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(path = "/add")
    public @ResponseBody Result addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepo.save(n);
        return new Result(1, "success");
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

}