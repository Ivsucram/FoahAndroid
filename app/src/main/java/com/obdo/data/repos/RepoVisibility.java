package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Visibility;

import java.sql.SQLException;

/**
 * Visibility custom DAO
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.Repo
 * @see com.obdo.data.models.Visibility
 */
public class RepoVisibility {
    /**
     * Visibility DAO - ORMlite version
     */
    Dao<Visibility, String> visibilityDAO;

    public RepoVisibility(DatabaseHelper db) {
        try {
            visibilityDAO = db.getVisibilityDAO();
        } catch (SQLException e) {
            db.onCreate(db.getReadableDatabase(), db.getConnectionSource());
            try {
                visibilityDAO = db.getVisibilityDAO();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Create a Visibility record
     * @param visibility record to be created
     * @return true if success, false if failure
     */
    public boolean create(Visibility visibility) {
        try {
            return visibilityDAO.create(visibility)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update a Visibility record
     * @param visibility record to be updated
     * @return true if success, false if failure
     */
    public boolean update(Visibility visibility) {
        try {
            return visibilityDAO.update(visibility)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a Visibility record
     * @param visibility record to be deleted
     * @return true if success, false if failure
     */
    public boolean delete(Visibility visibility) {
        try {
            return visibilityDAO.delete(visibility)>0?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
