package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.AccountDao;
import com.webapi.seed.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-04
 */
@Service
public class AccountService extends ServiceImpl<AccountDao, Account> {

    public List<Account> selectAll() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    public Account selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
