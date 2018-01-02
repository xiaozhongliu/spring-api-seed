package com.webapi.seed.service;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.seed.entity.Account;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IAccountService extends IService<Account> {

    List<Account> selectAll();

    Account selectByUsername(String username);

}
