package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.obdo.data.repos.Repo;

/**
 * Pin model
 * Note: The server DB have a different representation of this model, including the User on it. This model was created on the smartphone having in mind that only one user use the system at once.
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoPins
 */
public class Pin {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();

    public Pin() {}

    /**
     * Save pin record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        if (repo.Pins.checkPin(post)==null) {
            response = repo.Pins.create(this);
        } else {
            response = repo.Pins.update(this);
        }
        return response;
    }

    /**
     * Delete pin record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Pins.delete(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
