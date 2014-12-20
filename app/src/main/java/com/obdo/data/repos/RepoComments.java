package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Location;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoComments {
    Dao<Comment, String> commentDao;

    public RepoComments(DatabaseHelper db) {
        try {
            commentDao = db.getCommentDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Comment comment)
    {
        try {
            return commentDao.create(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Comment comment)
    {
        try {
            return commentDao.update(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Comment comment)
    {
        try {
            return commentDao.delete(comment);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    //TODO: get by Post

    public List<Comment> getAll()
    {
        try {
            return commentDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
