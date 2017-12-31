package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.BookmarkDao;
import com.webapi.seed.entity.Account;
import com.webapi.seed.entity.Bookmark;
import com.webapi.seed.service.IAccountService;
import com.webapi.seed.service.IBookmarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkDao, Bookmark> implements IBookmarkService {

    @Resource
    private IAccountService accountService;

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
