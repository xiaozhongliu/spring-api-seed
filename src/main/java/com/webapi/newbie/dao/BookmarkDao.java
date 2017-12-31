package com.webapi.newbie.dao;

import com.webapi.newbie.entity.Bookmark;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaozhong
 * @since 2017-12-31
 */
@Mapper
public interface BookmarkDao extends BaseMapper<Bookmark> {

}
