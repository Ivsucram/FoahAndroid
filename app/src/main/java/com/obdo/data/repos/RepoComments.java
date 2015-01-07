package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Comment custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Comment
 */
public class RepoComments {
    /**
     * Comment DAO - ORMlite version
     */
    Dao<Comment, Integer> commentDAO;

    public RepoComments(DatabaseHelper db) {
        try {
            commentDAO = db.getCommentDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                commentDAO = db.getCommentDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Comment record
     * @param comment record to be created
     * @return true if success, false if failure
     */
    public boolean create(Comment comment) {
        try {
            return commentDAO.create(comment)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Comment record
     * @param comment record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Comment comment) {
        try {
            return commentDAO.update(comment)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Comment record
     * @param comment record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Comment comment) {
        try {
            return commentDAO.delete(comment)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get a list of Comments existing in one Post
     * @param post Post that contain the comments
     * @return List of comments. It can be empty if there is no asset
     * @see com.obdo.data.models.Post
     */
    public List<Comment> getByPost(Post post) {
        try {
            return commentDAO.queryForEq("post", post);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Comment>();
    }
}
