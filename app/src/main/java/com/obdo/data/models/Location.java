package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.obdo.data.repos.Repo;

/**
 * Location model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoLocations
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

    /**
     * Save location record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        //TODO: Create a better check
        if (id!=null && !id.isEmpty()) {
            response = repo.Locations.update(this);
        } else {
            response = repo.Locations.create(this);
        }
        return response;
    }

    /**
     * Delete location record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Locations.delete(this);
    }

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
