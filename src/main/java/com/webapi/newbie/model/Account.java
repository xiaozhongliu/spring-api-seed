package com.webapi.newbie.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue
    public Long id;

    public String username;
    public String password;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.REMOVE })
    public Set<Bookmark> bookmarks;

    // default ctor required by jpa
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

}