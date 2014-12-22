package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Friend;
import com.obdo.data.models.Location;
import com.obdo.data.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Friend custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Friend
 */
public class RepoFriends {
    /**
     * User DAO - ORMlite version
     */
    Dao<Friend, String> friendDAO;

    public RepoFriends(DatabaseHelper db) {
        try {
            friendDAO = db.getFriendDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                friendDAO = db.getFriendDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a friend record
     * @param friend record to be created
     * @return true if success, false if failure
     */
    public boolean create(Friend friend) {
        try {
            return friendDAO.create(friend)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Friend record
     * @param friend record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Friend friend) {
        try {
            return friendDAO.update(friend)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Friend record
     * @param friend record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Friend friend) {
        boolean response1, response2;
        response1 = response2 = false;
        try {
            response1 = friendDAO.delete(friend)>0?true:false;
            response2 = friendDAO.delete(new Friend(friend.getUserB(),friend.getUserA()))>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (response1 || response2)?true:false;
    }

    /**
     * Check if friendship between users exists
     * @param userA User A
     * @param userB User B
     * @return Friend record if exists, null if not
     */
    public Friend getByUsers(User userA, User userB) {
        try {
            QueryBuilder<Friend, String> qb = friendDAO.queryBuilder();
            Where left = qb.where();
            Where right = qb.where();
            left.eq("userA", userA).and().eq("userB", userB);
            right.eq("userA", userB).and().eq("userB", userA);
            qb.where().or(left, right);

            PreparedQuery<Friend> pq = qb.prepare();

            return friendDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a list of Friends from an user
     * @param user User to search for friends
     * @return List of friends. It can be empty if there is no friends
     * @see com.obdo.data.models.User
     */
    public List<Friend> getByUser(User user) {
        try {
            QueryBuilder<Friend, String> qb = friendDAO.queryBuilder();
            Where left = qb.where();
            Where right = qb.where();
            left.eq("userA", user);
            right.eq("userB", user);
            qb.where().or(left, right);

            PreparedQuery<Friend> pq = qb.prepare();

            return friendDAO.query(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Friend>();
    }
}
