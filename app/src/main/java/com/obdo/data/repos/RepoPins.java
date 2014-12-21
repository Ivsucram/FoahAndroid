package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Location;
import com.obdo.data.models.Pin;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoPins {
    Dao<Pin, String> repoDao;

    public RepoPins(DatabaseHelper db) {
        try {
            repoDao = db.getPinDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Pin pin)
    {
        try {
            return repoDao.create(pin);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Pin pin)
    {
        try {
            return repoDao.update(pin);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Pin pin)
    {
        try {
            return repoDao.delete(pin);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<Pin> getAll()
    {
        try {
            return repoDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
