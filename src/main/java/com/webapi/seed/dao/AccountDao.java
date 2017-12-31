package com.webapi.seed.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.webapi.seed.entity.Account;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaozhong
 * @since 2017-12-31
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {

}
