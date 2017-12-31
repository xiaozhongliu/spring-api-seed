package com.webapi.newbie.service;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.newbie.entity.Account;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IAccountService extends IService<Account> {

    Iterable<Account> selectAll();

    Account selectByUsername(String username);

}
