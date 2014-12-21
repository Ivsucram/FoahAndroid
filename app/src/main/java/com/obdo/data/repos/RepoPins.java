package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Location;
import com.obdo.data.models.Pin;
import com.obdo.data.models.User;

import java.sql.SQLException;
import java.util.List;

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
    Dao<Pin, String> pinDao;

    public RepoPins(DatabaseHelper db) {
        try {
            pinDao = db.getPinDao();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                pinDao = db.getPinDao();
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
            return pinDao.create(pin)>0?true:false;
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
            return pinDao.update(pin)>0?true:false;
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
            return pinDao.delete(pin)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get Pin of a user
     * @param user User to bring pins
     * @return Pin of the user or null if there is none
     * @see com.obdo.data.models.Post
     */
    public Pin getByUser(User user) {
        try {
            QueryBuilder<Pin, String> qb = pinDao.queryBuilder();

            qb.where().eq("user", user);

            PreparedQuery<Pin> pq = qb.prepare();
            return pinDao.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
