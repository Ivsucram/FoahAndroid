package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Post;
import com.obdo.data.models.Visible;

import java.sql.SQLException;

/**
 * Visible custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Visible
 */
public class RepoVisibles {
    /**
     * Visible DAO - ORMlite version
     */
    private Dao<Visible, Integer> visibilityDAO;

    public RepoVisibles(DatabaseHelper db) {
        try {
            visibilityDAO = db.getVisibleDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                visibilityDAO = db.getVisibleDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Visible record
     * @param visible record to be created
     * @return true if success, false if failure
     */
    public boolean create(Visible visible) {
        try {
            return visibilityDAO.create(visible) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Visible record
     * @param visible record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Visible visible) {
        try {
            return visibilityDAO.update(visible) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Visible record
     * @param visible record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Visible visible) {
        try {
            return visibilityDAO.delete(visible) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check if post is visible to user
     * @param post Post to check
     * @return Visible if post is visible, null if not
     */
    public Visible checkPostIsVisible(Post post) {
        try {
            QueryBuilder<Visible, Integer> qb = visibilityDAO.queryBuilder();

            qb.where().eq("post", post);

            PreparedQuery<Visible> pq = qb.prepare();
            return visibilityDAO.queryForFirst(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
