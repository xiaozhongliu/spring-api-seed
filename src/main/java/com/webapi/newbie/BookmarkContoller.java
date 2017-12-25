package com.webapi.newbie;

import com.webapi.newbie.error.UserNotFoundException;
import com.webapi.newbie.model.Bookmark;
import com.webapi.newbie.model.Result;
import com.webapi.newbie.repo.AccountRepo;
import com.webapi.newbie.repo.BookmarkRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "users/{username}/bookmarks")
public class BookmarkContoller {

    private final BookmarkRepo bookmarkRepo;
    private final AccountRepo accountRepo;

    @Autowired
    BookmarkContoller(BookmarkRepo bookmarkRepo, AccountRepo accountRepo) {
        this.bookmarkRepo = bookmarkRepo;
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public @ResponseBody Result getBookmarkList(@PathVariable String username) {
        this.validateUser(username);

        Iterable<Bookmark> bookmarks = bookmarkRepo.findByAccountUsername(username);
        return new Result(1, "success", bookmarks);
    }

    @GetMapping(path = "/{bookmarkId}")
    public @ResponseBody Result getBookmark(@PathVariable String username, @PathVariable Long bookmarkId) {
        this.validateUser(username);

        Bookmark bookmark = bookmarkRepo.findOne(bookmarkId);
        return new Result(1, "success", bookmark);
    }

    @PostMapping
    public @ResponseBody Result addBookmark(@PathVariable String username, @RequestBody Bookmark input) {
        this.validateUser(username);

        Object result = this.accountRepo.findByUsername(username).map(account -> {
            Bookmark bookmark = bookmarkRepo.save(new Bookmark(account, input.uri, input.description));
            return bookmark;
        }).orElse(null);
        return new Result(1, "success", result);
    }

    /**
     * private helpers
     */
    private void validateUser(String username) {
        this.accountRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

}