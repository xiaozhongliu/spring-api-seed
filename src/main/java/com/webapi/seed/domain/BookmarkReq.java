package com.webapi.seed.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookmarkReq {

    public Long bookmarkId;
    public String description;
    public String uri;
    public Long accountId;

    public String username;

}
