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
public class AccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "account_role_id", type = IdType.AUTO)
    public Long accountRoleId;
    public String role;
    public Long accountId;

    public AccountRole(Long accountId, String role) {
        this.accountId = accountId;
        this.role = role;
    }

    public AccountRole() {
    }

}
