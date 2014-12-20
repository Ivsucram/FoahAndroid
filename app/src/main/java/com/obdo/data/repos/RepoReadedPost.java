package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Post;
import com.obdo.data.models.ReadedPost;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoReadedPost {
    Dao<ReadedPost, String> readedPostDao;

    public RepoReadedPost(DatabaseHelper db) {
        try {
            readedPostDao = db.getReadedPostDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(ReadedPost readedPost)
    {
        try {
            return readedPostDao.create(readedPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(ReadedPost readedPost)
    {
        try {
            return readedPostDao.update(readedPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(ReadedPost readedPost)
    {
        try {
            return readedPostDao.delete(readedPost);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<ReadedPost> getAll()
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
