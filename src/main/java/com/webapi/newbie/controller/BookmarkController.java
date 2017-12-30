package com.webapi.newbie.controller;

import com.webapi.newbie.entity.Account;
import com.webapi.newbie.entity.Bookmark;
import com.webapi.newbie.model.Result;
import com.webapi.newbie.service.impl.AccountServiceImpl;
import com.webapi.newbie.service.impl.BookmarkServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkServiceImpl bookmarkService;
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    @ResponseBody
    public Result getBookmarkList(@PathVariable String username) {
        this.validateUser(username);

        Iterable<Bookmark> bookmarks = bookmarkService.selectByUsername(username);
        return new Result(1, "success", bookmarks);
    }

    @GetMapping(path = "/{bookmarkId}")
    @ResponseBody
    public Result getBookmark(@PathVariable String username, @PathVariable Long bookmarkId) {
        this.validateUser(username);

        Bookmark bookmark = bookmarkService.selectById(bookmarkId);
        return new Result(1, "success", bookmark);
    }

    @PostMapping
    @ResponseBody
    public Result addBookmark(@PathVariable String username, @RequestBody Bookmark input) {
        this.validateUser(username);

        Account account = accountService.selectByUsername(username);
        bookmarkService.insert(new Bookmark(account.id, input.uri, input.description));
        return new Result(1, "success");
    }

    /**
     * private helpers
     */
    private void validateUser(String username) {
        accountService.selectByUsername(username);
    }

}
