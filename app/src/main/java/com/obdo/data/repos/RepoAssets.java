package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Asset;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Post;

import java.sql.SQLException;
import java.util.List;

/**
 * A
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Asset
 */
public class RepoAssets {
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

    public int create(Asset asset) {
        try {
            return assetDao.create(asset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Asset asset) {
        try {
            return assetDao.update(asset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(Asset asset) {
        try {
            return assetDao.delete(asset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Asset> getByPost(Post post) {
        try {
            return assetDao.queryForEq("post", post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Asset> getByComment(Comment comment) {
        try {
            return assetDao.queryForEq("comment", comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Asset> getAll() {
        try {
            return assetDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
