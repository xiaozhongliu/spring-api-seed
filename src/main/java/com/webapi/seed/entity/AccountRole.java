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
public class AccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public String role;
    public Long accountId;

    public AccountRole(Long accountId, String role) {
        this.accountId = accountId;
        this.role = role;
    }

    public AccountRole() {
    }

}
