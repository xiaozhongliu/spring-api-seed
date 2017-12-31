package com.webapi.newbie.service;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.newbie.entity.Bookmark;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IBookmarkService extends IService<Bookmark> {

    Bookmark selectById(Long bookmarkId);

    Iterable<Bookmark> selectByUsername(String username);

}
