package com.webapi.newbie.repo;

import com.webapi.newbie.model.Bookmark;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepo extends CrudRepository<Bookmark, Long> {

    Iterable<Bookmark> findByAccountUsername(String username);

    @Transactional
    void deleteByAccountUsername(String username);
}