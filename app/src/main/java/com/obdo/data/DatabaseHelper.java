package com.obdo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.obdo.data.models.Asset;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Friend;
import com.obdo.data.models.Location;
import com.obdo.data.models.Pin;
import com.obdo.data.models.Post;
import com.obdo.data.models.ReadPost;
import com.obdo.data.models.User;
import com.obdo.data.models.Visible;

import java.sql.SQLException;

/**
 * SQLite database open helper which manage when the application needs to create or upgrade its database.
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * Name of the app database file
     * @since 12/22/2014
     */
    private static final String DATABASE_NAME = "db.sqlite";
    /**
     * Version of the app database (in case we need to update the database on the future)
     * @since 12/22/2014
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Asset DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoAssets
     * @see com.obdo.data.models.Asset
     */
    private Dao<Asset, Integer> assetDAO = null;
    /**
     * Comment DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoComments
     * @see com.obdo.data.models.Comment
     */
    private Dao<Comment, Integer> commentDAO = null;
    /**
     * Friend DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoFriends
     * @see com.obdo.data.models.Friend
     */
    private Dao<Friend, Integer> friendDAO = null;
    /**
     * Location DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoLocations
     * @see com.obdo.data.models.Location
     */
    private Dao<Location, Integer> locationDAO = null;
    /**
     * Pin DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoPins
     * @see com.obdo.data.models.Pin
     */
    private Dao<Pin, Integer> pinDAO = null;
    /**
     * Post DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoPosts
     * @see com.obdo.data.models.Post
     */
    private Dao<Post, Integer> postDAO = null;
    /**
     * ReadPost DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoReadPosts
     * @see com.obdo.data.models.ReadPost
     */
    private Dao<ReadPost, Integer> readPostDAO = null;
    /**
     * User DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoUsers
     * @see com.obdo.data.models.User
     */
    private Dao<User, Integer> userDAO = null;
    /**
     * Visible DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoVisibles
     * @see com.obdo.data.models.Visible
     */
    private Dao<Visible, Integer> visibleDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Asset.class);
            TableUtils.createTable(connectionSource, Comment.class);
            TableUtils.createTable(connectionSource, Friend.class);
            TableUtils.createTable(connectionSource, Location.class);
            TableUtils.createTable(connectionSource, Pin.class);
            TableUtils.createTable(connectionSource, Post.class);
            TableUtils.createTable(connectionSource, ReadPost.class);
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Visible.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Asset.class, true);
            TableUtils.dropTable(connectionSource, Comment.class, true);
            TableUtils.dropTable(connectionSource, Friend.class, true);
            TableUtils.dropTable(connectionSource, Location.class, true);
            TableUtils.dropTable(connectionSource, Pin.class, true);
            TableUtils.dropTable(connectionSource, Post.class, true);
            TableUtils.dropTable(connectionSource, ReadPost.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Visible.class, true);
            onCreate(db);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a link between the database and the Asset DAO object
     * @return Asset DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Asset, Integer> getAssetDAO() throws SQLException {
        if (assetDAO == null) {
            assetDAO = DaoManager.createDao(getConnectionSource(), Asset.class);
        }
        return assetDAO;
    }

    /**
     * Create a link between the database and the Comment DAO object
     * @return Comment DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Comment, Integer> getCommentDAO() throws SQLException {
        if (commentDAO == null) {
            commentDAO = DaoManager.createDao(getConnectionSource(), Comment.class);
        }
        return commentDAO;
    }

    /**
     * Create a link between the database and the Friend DAO object
     * @return Friend DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Friend, Integer> getFriendDAO() throws SQLException {
        if (friendDAO == null) {
            friendDAO = DaoManager.createDao(getConnectionSource(), Friend.class);
        }
        return friendDAO;
    }

    /**
     * Create a link between the database and the Location DAO object
     * @return Location DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Location, Integer> getLocationDAO() throws SQLException {
        if (locationDAO == null) {
            locationDAO = DaoManager.createDao(getConnectionSource(), Location.class);
        }
        return locationDAO;
    }

    /**
     * Create a link between the database and the Pin DAO object
     * @return Pin DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Pin, Integer> getPinDAO() throws SQLException {
        if (pinDAO == null) {
            pinDAO = DaoManager.createDao(getConnectionSource(), Pin.class);
        }
        return pinDAO;
    }

    /**
     * Create a link between the database and the Post DAO object
     * @return Post DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Post, Integer> getPostDAO() throws SQLException {
        if (postDAO == null) {
            postDAO = DaoManager.createDao(getConnectionSource(), Post.class);
        }
        return postDAO;
    }

    /**
     * Create a link between the database and the ReadPost DAO object
     * @return ReadPost DAO object
     * @throws java.sql.SQLException
     */
    public Dao<ReadPost, Integer> getReadPostDAO() throws SQLException {
        if (readPostDAO == null) {
            readPostDAO = DaoManager.createDao(getConnectionSource(), ReadPost.class);
        }
        return readPostDAO;
    }

    /**
     * Create a link between the database and the User DAO object
     * @return User DAO object
     * @throws java.sql.SQLException
     */
    public Dao<User, Integer> getUserDAO() throws SQLException {
        if (userDAO == null) {
            userDAO = DaoManager.createDao(getConnectionSource(), User.class);
        }
        return userDAO;
    }

    /**
     * Create a link between the database and the ReadPost DAO object
     * @return ReadPost DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Visible, Integer> getVisibleDAO() throws SQLException {
        if (visibleDAO == null) {
            visibleDAO = DaoManager.createDao(getConnectionSource(), Visible.class);
        }
        return visibleDAO;
    }

    @Override
    public void close() {
        super.close();
        assetDAO = null;
        commentDAO = null;
        friendDAO = null;
        locationDAO = null;
        pinDAO = null;
        postDAO = null;
        readPostDAO = null;
        userDAO = null;
        visibleDAO = null;
    }
}
