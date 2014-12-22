package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.ReadPost;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoReadPosts {
    Dao<ReadPost, String> readedPostDao;

    public RepoReadPosts(DatabaseHelper db) {
        try {
            readedPostDao = db.getReadPostDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(ReadPost readPost)
    {
        try {
            return readedPostDao.create(readPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(ReadPost readPost)
    {
        try {
            return readedPostDao.update(readPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(ReadPost readPost)
    {
        try {
            return readedPostDao.delete(readPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<ReadPost> getAll()
    {
        try {
            return readedPostDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
