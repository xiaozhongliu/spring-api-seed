package com.webapi.newbie.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.REMOVE })
    private Set<Bookmark> bookmarks = new HashSet<>();

    // default ctor required by jpa
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }
}