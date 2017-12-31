package com.webapi.seed.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xiaozhong
 * @since 2017-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public Date lastPasswordResetDate;
    public String password;
    public String username;

    public Account() {
    }

}
