package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.obdo.data.repos.Repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Post model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoPosts
 */
public class Post {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = true)
    private String text;
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private User user = new User();
    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    private Location location = new Location();
    @ForeignCollectionField(eager = false)
    private Collection<Comment> comments;
    @ForeignCollectionField(eager = false)
    private Collection<Asset> assets;

    public Post() {}

    /**
     * Save post record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        //TODO: create a better check
        if (id!=null) {
            response = repo.Posts.update(this);
        } else {
            response = repo.Posts.create(this);
        }
        return response;
    }

    /**
     * Delete post record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Posts.delete(this);
    }

    /**
     * Get a list of Assets existing in one Post
     * @param repo db
     * @return List of assets. It can be empty if there is no asset
     * @see com.obdo.data.models.Asset
     */
    public List<Asset> getAssets(Repo repo) {
        return repo.Assets.getByPost(this);
    }

    /**
     * Get a list of Comments existing in one Post
     * @param repo db
     * @return List of comments. It can be empty if there is no asset
     * @see com.obdo.data.models.Comment
     */
    public List<Comment> getComments(Repo repo) {
        return repo.Comments.getByPost(this);
    }

    /**
     * Get location of a post
     * @param repo db
     * @return Location of the post or null if there is none
     * @see com.obdo.data.models.Location
     */
    public Location getLocation(Repo repo) {
        return repo.Locations.getByPost(this);
    }

    /**
     * Check if post is pinned
     * @param repo db
     * @return Pin with pinned post if is pinned or null if it is not pinned
     * @see com.obdo.data.models.Pin
     */
    public Pin isPin(Repo repo) {
        return repo.Pins.checkPin(this);
    }

    /**
     * Check if post was read
     * @param repo db
     * @return ReadPost with read post if it was read or null if it is was not read yet
     * @see com.obdo.data.models.ReadPost
     */
    public ReadPost isRead(Repo repo) {
        return repo.ReadPosts.checkPostIsRead(this);
    }

    /**
     * Check if post is visible by cellphone user
     * @param repo db
     * @return Visible if post is visible, null if not
     * @see com.obdo.data.models.Visible
     */
    public Visible isVisible(Repo repo) {
        return repo.Visibles.checkPostIsVisible(this);
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Collection<Asset> assets) {
        this.assets = assets;
    }
}
