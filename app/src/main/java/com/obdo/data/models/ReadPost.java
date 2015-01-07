package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.obdo.data.repos.Repo;

/**
 * ReadPost model
 * It holds read posts and the last read comment of this post
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoReadPosts
 */
public class ReadPost {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true, canBeNull = false)
    private Post post = new Post();
    @DatabaseField(foreign = true, canBeNull = true)
    private Comment lastComment = new Comment();

    public ReadPost() {}

    public ReadPost(Post post, Comment lastComment) {
        this.post = post;
        this.lastComment = lastComment;
    }

    /**
     * Save readpost record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        if (repo.ReadPosts.checkPostIsRead(post)==null) {
            response = repo.ReadPosts.create(this);
        } else {
            response = repo.ReadPosts.update(this);
        }
        return response;
    }

    /**
     * Delete readpost record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.ReadPosts.delete(this);
    }
}
