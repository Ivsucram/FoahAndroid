package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.ReadPost;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadPost custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.ReadPost
 */
public class RepoReadPosts {
    /**
     * ReadPost DAO - ORMlite version
     */
    Dao<ReadPost, String> readedPostDao;

    public RepoReadPosts(DatabaseHelper db) {
        try {
            readedPostDao = db.getReadPostDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                readedPostDao = db.getReadPostDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a ReadPost record
     * @param readPost record to be created
     * @return true if success, false if failure
     */
    public boolean create(ReadPost readPost) {
        try {
            return readedPostDao.create(readPost)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a ReadPost record
     * @param readPost record to be updated
     * @return true if success, false if failure
     */
    public boolean update(ReadPost readPost) {
        try {
            return readedPostDao.update(readPost)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a ReadPost record
     * @param readPost record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(ReadPost readPost) {
        try {
            return readedPostDao.delete(readPost)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get all ReadPost
     * @return List of ReadPost, empyt is there is none
     */
    public List<ReadPost> getAll() {
        try {
            return readedPostDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<ReadPost>();
    }
}
