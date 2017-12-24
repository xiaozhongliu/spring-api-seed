package com.webapi.newbie;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webapi.newbie.model.Account;
import com.webapi.newbie.model.Bookmark;
import com.webapi.newbie.repo.AccountRepo;
import com.webapi.newbie.repo.BookmarkRepo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BookmarkContollerTest {

    private MediaType contentType = MediaType.APPLICATION_JSON_UTF8;
    private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
    private MockMvc mockMvc;

    private String username = "unittest";
    private Account account;
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private BookmarkRepo bookmarkRepo;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<Object>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.account = accountRepo.save(new Account(username, "password"));
        this.bookmarkList
                .add(bookmarkRepo.save(new Bookmark("http://bookmark.com/1/" + username, "A description", account)));
        this.bookmarkList
                .add(bookmarkRepo.save(new Bookmark("http://bookmark.com/2/" + username, "A description", account)));
    }

    @After
    public void unload() throws Exception {
        this.accountRepo.deleteByUsername(username);
    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/users/fake/bookmarks").content(this.json(new Bookmark())).contentType(contentType))
                .andExpect((status().isNotFound()));
    }

    @Test
    public void readSingleBookmark() throws Exception {
        int bookmarkId = this.bookmarkList.get(0).getId().intValue();
        mockMvc.perform(get("/users/" + username + "/bookmarks/" + bookmarkId)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.data.id", is(bookmarkId)))
                .andExpect(jsonPath("$.data.uri", is("http://bookmark.com/1/" + username)))
                .andExpect(jsonPath("$.data.description", is("A description")));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
