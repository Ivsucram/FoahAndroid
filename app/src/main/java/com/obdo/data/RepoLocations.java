package com.obdo.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.models.Location;
import com.obdo.data.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoLocations {

    Dao<Location, String> locationDao;

    public RepoLocations(DatabaseHelper db) {
        try {
            locationDao = db.getLocationDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Location location)
    {
        try {
            return locationDao.create(location);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Location location)
    {
        try {
            return locationDao.update(location);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Location location)
    {
        try {
            return locationDao.delete(location);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    //TODO: get by latitude and logintude - USE GOOGLE MAP LOCATION OBJECT
    public Location getByLatitudeLogintude(float latitude, float logintude)
    {
        try {
            QueryBuilder<Location, String> qb = locationDao.queryBuilder();

            qb.where().eq("latitude", latitude).eq("logintude", logintude);

            PreparedQuery<Location> pq = qb.prepare();
            return locationDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
    public List<Location> getAll()
    {
        try {
            return locationDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
