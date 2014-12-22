package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.obdo.data.repos.Repo;

/**
 * Asset model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoAssets
 */
public class Asset {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = true)
    private String file;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Comment comment = new Comment();

    /**
     * Save asset record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        if (id!=null && !id.isEmpty()) {
            response = repo.Assets.update(this);
        } else {
            response = repo.Assets.create(this);
        }
        return response;
    }

    /**
     * Delete asset record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Assets.delete(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
