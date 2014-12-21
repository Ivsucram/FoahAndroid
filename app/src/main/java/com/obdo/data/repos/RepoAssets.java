package com.obdo.data.repos;

import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Asset;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Asset custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Asset
 */
public class RepoAssets {
    /**
     * Asset DAO - ORMlite version
     */
    Dao<Asset, String> assetDao;

    public RepoAssets(DatabaseHelper db) {
        try {
            assetDao = db.getAssetDao();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(),db.getConnectionSource());
            try {
                assetDao = db.getAssetDao();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create an Asset record
     * @param asset record to be created
     * @return true if success, false if failure
     */
    public boolean create(Asset asset) {
        try {
            return assetDao.create(asset)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update an Asset record
     * @param asset record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Asset asset) {
        try {
            return assetDao.update(asset)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete an Asset record
     * @param asset record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Asset asset) {
        try {
            return assetDao.delete(asset)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get a list of Assets existing in one Post
     * @param post Post that contain the assets
     * @return List of assets. It can be empty if there is no asset
     * @see com.obdo.data.models.Post
     */
    public List<Asset> getByPost(Post post) {
        try {
            return assetDao.queryForEq("post", post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Asset>();
    }

    /**
     * Get a list of Assets existing in one comment
     * @param comment Comment that contain the assets
     * @return List of assets. It can be empty if there is no asset
     * @see com.obdo.data.models.Comment
     */
    public List<Asset> getByComment(Comment comment) {
        try {
            return assetDao.queryForEq("comment", comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Asset>();
    }
}
