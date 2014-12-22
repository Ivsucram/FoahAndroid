package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Pin;
import com.obdo.data.models.Post;

import java.sql.SQLException;

/**
 * Pin custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Pin
 */
public class RepoPins {
    /**
     * Pin DAO - ORMlite version
     */
    Dao<Pin, Integer> pinDAO;

    public RepoPins(DatabaseHelper db) {
        try {
            pinDAO = db.getPinDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                pinDAO = db.getPinDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Pin record
     * @param pin record to be created
     * @return true if success, false if failure
     */
    public boolean create(Pin pin) {
        try {
            return pinDAO.create(pin)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Pin record
     * @param pin record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Pin pin) {
        try {
            return pinDAO.update(pin)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Pin record
     * @param pin record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Pin pin) {
        try {
            return pinDAO.delete(pin)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check if post is pinned
     * @param post Post to check if is pinned
     * @return Pin with pinned post if is pinned or null if it is not pinned
     * @see com.obdo.data.models.Post
     */
    public Pin checkPin(Post post) {
        try {
            QueryBuilder<Pin, Integer> qb = pinDAO.queryBuilder();

            qb.where().eq("post", post);

            PreparedQuery<Pin> pq = qb.prepare();
            return pinDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
