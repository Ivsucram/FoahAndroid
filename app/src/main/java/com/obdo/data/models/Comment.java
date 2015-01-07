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
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = true)
    private String text;
    @DatabaseField(foreign = true, canBeNull = false)
    private User user = new User();
    @DatabaseField(foreign = true, canBeNull = false)
    private Post post = new Post();
    @ForeignCollectionField(eager = true)
    private Collection<Asset> assets = new ArrayList<Asset>();

    public Comment() {}

    /**
     * Save comment record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        if (id!=null) {
            response = repo.Comments.update(this);
        } else {
            response = repo.Comments.create(this);
        }
        return response;
    }

    /**
     * Delete comment record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Comments.delete(this);
    }

    /**
     * Get list of assets from the db
     * @param repo db
     * @return List of Assets. It can be empty if there is no asset
     * @see com.obdo.data.models.Asset
     */
    public List<Asset> getAssets(Repo repo) {
        return repo.Assets.getByComment(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
