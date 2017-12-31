package com.webapi.newbie.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.newbie.dao.BookmarkDao;
import com.webapi.newbie.entity.Account;
import com.webapi.newbie.entity.Bookmark;
import com.webapi.newbie.service.IAccountService;
import com.webapi.newbie.service.IBookmarkService;

import org.springframework.stereotype.Service;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkDao, Bookmark> implements IBookmarkService {

    @Resource
    IAccountService accountService;

    @Override
    public Bookmark selectById(Long bookmarkId) {
        return baseMapper.selectById(bookmarkId);
    }

    @Override
    public List<Bookmark> selectByUsername(String username) {
        Account account = accountService.selectByUsername(username);
        return baseMapper.selectList(new EntityWrapper<Bookmark>().eq("account_id", account.id));
    }

}
