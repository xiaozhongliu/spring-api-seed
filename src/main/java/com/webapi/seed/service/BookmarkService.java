package com.webapi.seed.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webapi.seed.dao.BookmarkDao;
import com.webapi.seed.entity.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2018-01-04
 */
@Service
public class BookmarkService extends ServiceImpl<BookmarkDao, Bookmark> {

    public Bookmark selectById(Long bookmarkId) {
        return baseMapper.selectById(bookmarkId);
    }

    public List<Bookmark> selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
