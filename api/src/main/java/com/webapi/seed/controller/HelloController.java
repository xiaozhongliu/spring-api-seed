package com.webapi.seed.controller;

import com.webapi.seed.config.PackConfig;
import com.webapi.seed.controller.base.BaseController;
import com.webapi.seed.domain.Foo;
import com.webapi.seed.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author xiaozhong
 * @since 2018-01-25
 */
@RefreshScope
@Controller
@RequestMapping("/hello")
public class HelloController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private PackConfig config;

    @GetMapping
    public ResponseEntity testHystrix(@RequestHeader String head, @RequestParam String query) {
        int sleepTime = new Random().nextInt(3000);
        String prompt = String.format("sleeped for: %sms", sleepTime);
//        try {
//            Thread.sleep(sleepTime);
//            logger.info(prompt);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }

        logger.info("from config center: {}", config.test);

        Map<String, String> map = new HashMap<>();
        map.put("head", head);
        map.put("query", query);
        map.put("prompt", prompt);
        return Result.Ok(map);
    }

    @PostMapping
    public ResponseEntity testPost(@RequestBody Foo foo) {
        return Result.Ok(foo);
    }
}
