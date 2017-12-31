package com.webapi.seed.service;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.seed.entity.AccountRole;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IAccountRoleService extends IService<AccountRole> {

    List<AccountRole> selectByUsername(String username);

}
