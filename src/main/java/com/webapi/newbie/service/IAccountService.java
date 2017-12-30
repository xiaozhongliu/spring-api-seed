package com.webapi.newbie.service;

import com.webapi.newbie.entity.Account;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IAccountService extends IService<Account> {

    Iterable<Account> selectAll();

    Account selectByUsername(String username);

}
