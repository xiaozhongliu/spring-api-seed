package com.webapi.seed.service;

import com.webapi.seed.dao.AccountRoleDao;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.entity.AccountRole;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-04
 */
@Service
public class AccountRoleService extends ServiceImpl<AccountRoleDao, AccountRole> {

    public List<AccountRole> selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
