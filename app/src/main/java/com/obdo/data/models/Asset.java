package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Asset model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoAssets
 */
public class Asset {
    @DatabaseField(id = true)
    private String string;
    @DatabaseField(canBeNull = true)
    private String file;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Comment comment = new Comment();

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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
