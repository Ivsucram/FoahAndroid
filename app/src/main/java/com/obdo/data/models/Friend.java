package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.obdo.data.repos.Repo;

/**
 * Friend model
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoFriends
 */
public class Friend {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true)
    private User userA;
    @DatabaseField(foreign = true)
    private User userB;

    public Friend() {}

    public Friend(User userA, User userB) {
        this.userA = userA;
        this.userB = userB;
    }

    /**
     * Save friend record on db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean save(Repo repo) {
        boolean response = false;
        if (repo.Friends.checkFriendship(userA, userB)==null) {
            response = repo.Friends.create(this);
        } else {
            response = repo.Friends.update(this);
        }
        return response;
    }

    /**
     * Delete friend record from db
     * @param repo db
     * @return true if success, false if failure
     */
    public boolean delete(Repo repo) {
        return repo.Friends.delete(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public User getUserB() {
        return userB;
    }

    public void setUserB(User userB) {
        this.userB = userB;
    }
}
