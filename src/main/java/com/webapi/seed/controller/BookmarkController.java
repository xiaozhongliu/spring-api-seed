package com.webapi.seed.controller;

import com.webapi.seed.domain.Result;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.Bookmark;
import com.webapi.seed.service.IAccountService;
import com.webapi.seed.service.IBookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private IBookmarkService bookmarkService;
    @Autowired
    private IAccountService accountService;

    @GetMapping
    @ResponseBody
    public ResponseEntity getBookmarkList(@PathVariable String username) {
        this.validateUser(username);

        Iterable<Bookmark> bookmarks = bookmarkService.selectByUsername(username);
        return Result.Ok(bookmarks);
    }

    @GetMapping(path = "/{bookmarkId}")
    @ResponseBody
    public ResponseEntity getBookmark(@PathVariable String username, @PathVariable Long bookmarkId) {
        this.validateUser(username);

        Bookmark bookmark = bookmarkService.selectById(bookmarkId);
        return Result.Ok(bookmark);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addBookmark(@PathVariable String username, @RequestBody Bookmark input) {
        this.validateUser(username);

        Account account = accountService.selectByUsername(username);
        bookmarkService.insert(new Bookmark(account.id, input.uri, input.description));
        return Result.Ok();
    }

    /**
     * private helpers
     */
    private void validateUser(String username) {
        accountService.selectByUsername(username);
    }

}
