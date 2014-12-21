package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.obdo.data.repos.Repo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class User {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private String phoneNumber;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = true)
    private String picture;
    @ForeignCollectionField(eager = false)
    private Collection<Post> posts = new ArrayList<Post>();
    @ForeignCollectionField(eager = false)
    private Collection<Comment> comments = new ArrayList<Comment>();
    @ForeignCollectionField(eager = false)
    private Collection<Pin> pins = new ArrayList<Pin>();
    //TODO: make friends relationship

    public User() {}
    public User(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String phoneNumber, String name, String picture) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.picture = picture;
    }

    public int save(Repo repo) {
        int response;
        if (repo.Users.getByPhoneNumber(phoneNumber) == null) {
            response = repo.Users.create(this);
        } else {
            response = repo.Users.update(this);
        }
        return response;
    }

    public int delete(Repo repo) {
        return repo.Users.delete(this);
    }

    public String toString() {
        return "";
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
}
