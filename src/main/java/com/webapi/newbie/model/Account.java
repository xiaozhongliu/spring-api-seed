package com.webapi.newbie.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @Column(nullable = false)
    public String username;
    @Column(nullable = false)
    public String password;
    public Date lastPasswordResetDate;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.REMOVE })
    public Set<Bookmark> bookmarks;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.REMOVE })
    public Set<AccountRole> roles;

    // default ctor required by jpa
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

}