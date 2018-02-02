package com.webapi.seed.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookmarkReq {

    @NotBlank(message = "用户名不能为空")
    private String username;
    private Long bookmarkId;
    private String description;
    @NotBlank(message = "地址不能为空")
    private String uri;
}
