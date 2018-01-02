package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.AccountRoleDao;
import com.webapi.seed.entity.AccountRole;
import com.webapi.seed.service.IAccountRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-02
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleDao, AccountRole> implements IAccountRoleService {

    @Override
    public List<AccountRole> selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
