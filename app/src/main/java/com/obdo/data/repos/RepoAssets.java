package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Asset;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoAssets {
    Dao<Asset, String> assetDao;

    public RepoAssets(DatabaseHelper db) {
        try {
            assetDao = db.getAssetDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Asset asset)
    {
        try {
            return assetDao.create(asset);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Asset asset)
    {
        try {
            return assetDao.update(asset);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Asset asset)
    {
        try {
            return assetDao.delete(asset);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    //TODO: get by Post - list
    //TODO: get by comment - list

    public List<Asset> getAll()
    {
        try {
            return assetDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
