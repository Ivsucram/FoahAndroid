package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.User;

import java.sql.SQLException;

/**
 * User custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.User
 */
public class RepoUsers {
    /**
     * User DAO - ORMlite version
     */
    Dao<User, String> userDAO;

    public RepoUsers(DatabaseHelper db) {
        try {
            userDAO = db.getUserDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                userDAO = db.getUserDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a User record
     * @param user record to be created
     * @return true if success, false if failure
     */
    public boolean create(User user) {
        try {
            return userDAO.create(user)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a User record
     * @param user record to be updated
     * @return true if success, false if failure
     */
    public boolean update(User user) {
        try {
            return userDAO.update(user)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a User record
     * @param user record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(User user) {
        try {
            return userDAO.delete(user)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get User by phone number
     * @param phoneNumber User phone number
     * @return User that has that phone number if it exist or null if there is none
     */
    public User getByPhoneNumber(String phoneNumber) {
        try {
            QueryBuilder<User, String> qb = userDAO.queryBuilder();

            qb.where().eq("phoneNumber", phoneNumber);

            PreparedQuery<User> pq = qb.prepare();
            return userDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
