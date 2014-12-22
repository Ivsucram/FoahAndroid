package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.obdo.data.repos.Repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Comment model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoComments
 */
public class Comment {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = true)
    private String text;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user = new User();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
    @ForeignCollectionField(eager = false)
    private Collection<Asset> assets = new ArrayList<Asset>();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ReadPost readPost = new ReadPost();

    public List<Asset> getAssets(Repo repo) {
        return repo.Assets.getByComment(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Collection<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Collection<Asset> assets) {
        this.assets = assets;
    }

    public ReadPost getReadPost() {
        return readPost;
    }

    public void setReadPost(ReadPost readPost) {
        this.readPost = readPost;
    }
}
