package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Post;
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
    private Dao<ReadPost, Integer> readedPostDAO;

    public RepoReadPosts(DatabaseHelper db) {
        try {
            readedPostDAO = db.getReadPostDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                readedPostDAO = db.getReadPostDAO();
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
            return readedPostDAO.create(readPost) > 0;
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
            return readedPostDAO.update(readPost) > 0;
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
            return readedPostDAO.delete(readPost) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check if post is read
     * @param post Post to check
     * @return ReadPost if post was read, null if not
     */
    public ReadPost checkPostIsRead(Post post) {
        try {
            QueryBuilder<ReadPost, Integer> qb = readedPostDAO.queryBuilder();

            qb.where().eq("post", post);

            PreparedQuery<ReadPost> pq = qb.prepare();
            return readedPostDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all ReadPost
     * @return List of ReadPost, empty is there is none
     */
    public List<ReadPost> getAll() {
        try {
            return readedPostDAO.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
