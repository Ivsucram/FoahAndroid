package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Location {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private float latitude;
    @DatabaseField(canBeNull = false)
    private float longitude;
    @DatabaseField(canBeNull = true)
    private float radius;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
