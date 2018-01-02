package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.AccountDao;
import com.webapi.seed.entity.Account;
import com.webapi.seed.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-02
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements IAccountService {

    @Override
    public List<Account> selectAll() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public Account selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
