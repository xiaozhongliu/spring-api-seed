package com.webapi.newbie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Bookmark {

    @Id
    @GeneratedValue
    public Long id;

    public String uri;
    public String description;

    @JsonIgnore
    @ManyToOne
    public Account account;

    // default ctor required by jpa
    public Bookmark() {
    }

    public Bookmark(String uri, String description, Account account) {
        this.uri = uri;
        this.description = description;
        this.account = account;
    }

}