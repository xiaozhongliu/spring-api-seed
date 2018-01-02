package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.BookmarkDao;
import com.webapi.seed.entity.Bookmark;
import com.webapi.seed.service.IBookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-02
 */
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkDao, Bookmark> implements IBookmarkService {

    @Override
    public Bookmark selectById(Long bookmarkId) {
        return baseMapper.selectById(bookmarkId);
    }

    @Override
    public List<Bookmark> selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
