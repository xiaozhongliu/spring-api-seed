package com.webapi.seed.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiaozhong
 * @since 2017-12-31
 */
@Data
@Accessors(chain = true)
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public String description;
    public String uri;
    public Long accountId;

    public Bookmark(Long accountId, String description, String uri) {
        this.accountId = accountId;
        this.description = description;
        this.uri = uri;
    }

    public Bookmark() {
    }

}
