package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.obdo.data.repos.Repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoUsers
 */
public class User {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private String phoneNumber;
    @DatabaseField(canBeNull = true)
    private String name;
    @DatabaseField(canBeNull = true)
    private String picture;
    @ForeignCollectionField(eager = false)
    private Collection<Post> posts = new ArrayList<Post>();
    @ForeignCollectionField(eager = false)
    private Collection<Comment> comments = new ArrayList<Comment>();
    @ForeignCollectionField(eager = false)
    private Collection<Pin> pins = new ArrayList<Pin>();

    public User() {}

    public User(String phoneNumber)  {
        this.phoneNumber = phoneNumber;
    }

    public User(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String phoneNumber, String name, String picture) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.picture = picture;
    }

    /**
     * Save user record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response;
        if (repo.Users.getByPhoneNumber(phoneNumber) == null) {
            response = repo.Users.create(this);
        } else {
            response = repo.Users.update(this);
        }
        return response;
    }

    /**
     * Delete user record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Users.delete(this);
    }

    /**
     * Get a list of friends from user
     * @param repo db
     * @return List of friends. It can be empty if there is no friends
     * @see com.obdo.data.models.Friend
     */
    public List<Friend> getFriends(Repo repo) {
        return repo.Friends.getByUser(this);
    }

    /**
     * Get a list of posts created by user
     * @param repo db
     * @return List of posts. It can be empty if there is no posts
     * @see com.obdo.data.models.Post
     */
    public List<Post> getPosts(Repo repo) {
        return repo.Posts.getByUser(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Pin> getPins() {
        return pins;
    }

    public void setPins(Collection<Pin> pins) {
        this.pins = pins;
    }
}
