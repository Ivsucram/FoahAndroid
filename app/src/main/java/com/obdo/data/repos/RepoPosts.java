package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Location;
import com.obdo.data.models.Post;
import com.obdo.data.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Post custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Post
 * @see com.obdo.data.models.Location
 */
public class RepoPosts {
    /**
     * Post DAO - ORMlite version
     */
    Dao<Post, String> postDAO;
    /**
     * Location DAO - ORMlite version
     */
    Dao<Location, String> locationDAO;

    public RepoPosts(DatabaseHelper db) {
        try {
            postDAO = db.getPostDAO();
            locationDAO = db.getLocationDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                postDAO = db.getPostDAO();
                locationDAO = db.getLocationDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Post record
     * @param post record to be created
     * @return true if success, false if failure
     */
    public boolean create(Post post) {
        try {
            return postDAO.create(post)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Post record
     * @param post record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Post post) {
        try {
            return postDAO.update(post)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Post record
     * @param post record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Post post) {
        try {
            return postDAO.delete(post)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get a list of Posts created by one user
     * @param user User that created the posts
     * @return List of posts. It can be empty if there is no asset
     * @see com.obdo.data.models.User
     */
    public List<Post> getByPost(User user) {
        try {
            return postDAO.queryForEq("user", user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Post>();
    }

    /**
     * Get a list of Posts at location
     * @param location location center
     * @param radius radius os the circle
     * @return List of posts. It can be empty if there is no asset
     * @see com.obdo.data.models.User
     */
    public List<Post> getByLocation(android.location.Location location, float radius) {
        try {
            double earthRadius = 6371000;//meters

            QueryBuilder<Location, String> qb = locationDAO.queryBuilder();
            qb.where().between("latitude", location.getLatitude()-(radius/earthRadius), location.getLatitude()+(radius/earthRadius));
            qb.where().and();
            qb.where().between("longitude", location.getLongitude()-(radius/earthRadius), location.getLongitude()+(radius/earthRadius));
            PreparedQuery<Location> pq = qb.prepare();

            List<Location> listLocation = locationDAO.query(pq);

            List<Post> listPost = new ArrayList<Post>();
            for (Location l : listLocation) {
                double fi1 = Math.toRadians(location.getLatitude());
                double fi2 = Math.toRadians((l.getLatitude()));
                double mediumFi = Math.toRadians(l.getLatitude()-location.getLatitude());
                double mediumLambda = Math.toRadians(l.getLongitude()-location.getLongitude());

                double a = Math.sin(mediumFi/2) * Math.sin(mediumFi/2) + Math.cos(fi1) + Math.cos(fi2) * Math.sin(mediumLambda/2) * Math.sin(mediumLambda/2);
                double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
                double d = earthRadius * c;

                if (radius <= d) listPost.add(l.getPost());
            }
            return listPost;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Post>();
    }
}
