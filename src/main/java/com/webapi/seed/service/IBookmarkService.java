package com.webapi.seed.service;

import com.baomidou.mybatisplus.service.IService;
import com.webapi.seed.entity.Bookmark;

import java.util.List;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
public interface IBookmarkService extends IService<Bookmark> {

    Bookmark selectById(Long bookmarkId);

    List<Bookmark> selectByUsername(String username);

}
