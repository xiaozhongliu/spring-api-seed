package com.webapi.newbie;

// import java.util.Arrays;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.webapi.newbie.entity.Account;
// import com.webapi.newbie.entity.Bookmark;
// import com.webapi.newbie.repo.AccountRepo;
// import com.webapi.newbie.repo.BookmarkRepo;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // @Bean
    // CommandLineRunner init(AccountRepo accountRepo, BookmarkRepo bookmarkRepo) {
    //     return evt -> Arrays.asList(
    //             new String[] { "jhoeller", "dsyer", "pwebb", "ogierke", "rwinch", "mfisher", "mpollack", "jlong" })
    //             .forEach(username -> {
    //                 Account account = accountRepo.save(new Account(username, "password"));
    //                 bookmarkRepo.save(new Bookmark(account, "http://bookmark.com/1/" + username, "A description"));
    //                 bookmarkRepo.save(new Bookmark(account, "http://bookmark.com/2/" + username, "A description"));
    //             });
    // }

}
