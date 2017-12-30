package com.webapi.newbie.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public String description;
    public String uri;
    public Long accountId;

    public Bookmark() {
    }

    public Bookmark(Long accountId, String description, String uri) {
        this.accountId = accountId;
        this.description = description;
        this.uri = uri;
    }

}
