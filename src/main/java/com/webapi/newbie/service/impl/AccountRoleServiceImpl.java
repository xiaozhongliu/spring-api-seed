package com.webapi.newbie.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.newbie.dao.AccountRoleDao;
import com.webapi.newbie.entity.Account;
import com.webapi.newbie.entity.AccountRole;
import com.webapi.newbie.service.IAccountRoleService;
import com.webapi.newbie.service.IAccountService;

import org.springframework.stereotype.Service;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleDao, AccountRole> implements IAccountRoleService {

    @Resource
    IAccountService accountService;

    @Override
    public List<AccountRole> selectByUsername(String username) {
        Account account = accountService.selectByUsername(username);
        AccountRole role = new AccountRole(account.id, null);
        return baseMapper.selectList(new EntityWrapper<AccountRole>(role));
    }

}
