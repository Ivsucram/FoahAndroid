package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Friend;

import java.sql.SQLException;

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
        try {
            return friendDAO.delete(friend)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
