package com.webapi.seed.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiaozhong
 * @since 2018-01-02
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bookmark_id", type = IdType.AUTO)
    private Long bookmarkId;
    private String description;
    private String uri;
    private Long accountId;

    public Bookmark(Long accountId, String description, String uri) {
        this.accountId = accountId;
        this.description = description;
        this.uri = uri;
    }

    public Bookmark() {
    }

}
