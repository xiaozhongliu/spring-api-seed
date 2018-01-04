package com.webapi.seed.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookmarkReq {

    @NotBlank(message = "用户名不能为空")
    public String username;
    public Long bookmarkId;
    public String description;
    @NotBlank(message = "地址不能为空")
    public String uri;

}
