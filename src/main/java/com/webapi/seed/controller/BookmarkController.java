package com.webapi.seed.controller;

import com.webapi.seed.controller.base.BaseController;
import com.webapi.seed.domain.BookmarkReq;
import com.webapi.seed.domain.Result;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.Bookmark;
import com.webapi.seed.service.impl.AccountService;
import com.webapi.seed.service.impl.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/bookmark")
public class BookmarkController extends BaseController {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    @ResponseBody
    public ResponseEntity getBookmarkList(@RequestParam String username) {
        this.validateUser(username);

        Iterable<Bookmark> bookmarks = bookmarkService.selectByUsername(username);
        return Result.Ok(bookmarks);
    }

    @GetMapping(path = "/{bookmarkId}")
    @ResponseBody
    public ResponseEntity getBookmark(@PathVariable Long bookmarkId) {
        Bookmark bookmark = bookmarkService.selectById(bookmarkId);
        return Result.Ok(bookmark);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addBookmark(@Valid @RequestBody BookmarkReq bookmark) {
        Account account = this.validateUser(bookmark.username);
        bookmarkService.insert(new Bookmark(account.accountId, bookmark.uri, bookmark.description));
        return Result.Ok();
    }

    /**
     * private helpers
     */
    private Account validateUser(String username) {
        return accountService.selectByUsername(username);
    }

}
