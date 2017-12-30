package com.webapi.newbie.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.webapi.newbie.entity.Bookmark;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaozhong
 * @since 2017-12-30
 */
@Mapper
public interface BookmarkDao extends BaseMapper<Bookmark> {

}
