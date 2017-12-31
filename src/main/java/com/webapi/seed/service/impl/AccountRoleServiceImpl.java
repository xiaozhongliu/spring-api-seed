package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.AccountRoleDao;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.AccountRole;
import com.webapi.seed.service.IAccountRoleService;
import com.webapi.seed.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleDao, AccountRole> implements IAccountRoleService {

    @Resource
    private IAccountService accountService;

    @Override
    public List<AccountRole> selectByUsername(String username) {
        Account account = accountService.selectByUsername(username);
        AccountRole role = new AccountRole(account.id, null);
        return baseMapper.selectList(new EntityWrapper<>(role));
    }

}
