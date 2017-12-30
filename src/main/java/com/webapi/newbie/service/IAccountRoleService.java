package com.webapi.newbie.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.newbie.entity.AccountRole;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IAccountRoleService extends IService<AccountRole> {

    List<AccountRole> selectByUsername(String username);

}
