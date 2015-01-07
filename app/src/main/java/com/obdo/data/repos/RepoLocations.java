package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Location;
import com.obdo.data.models.Post;

import java.sql.SQLException;

/**
 * Location custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Location
 */
public class RepoLocations {
    /**
     * Location DAO - ORMlite version
     */
    Dao<Location, Integer> locationDAO;

    public RepoLocations(DatabaseHelper db) {
        try {
            locationDAO = db.getLocationDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                locationDAO = db.getLocationDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Location record
     * @param location record to be created
     * @return true if success, false if failure
     */
    public boolean create(Location location) {
        try {
            return locationDAO.create(location)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Location record
     * @param location record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Location location) {
        try {
            return locationDAO.update(location)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Location record
     * @param location record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Location location) {
        try {
            return locationDAO.delete(location)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get location of a post
     * @param post Post to check location
     * @return Location of the post or null if there is none
     * @see com.obdo.data.models.Post
     */
    public Location getByPost(Post post) {
        try {
            QueryBuilder<Location, Integer> qb = locationDAO.queryBuilder();

            qb.where().eq("post", post);

            PreparedQuery<Location> pq = qb.prepare();
            return locationDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
