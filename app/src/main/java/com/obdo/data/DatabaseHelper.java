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
import com.obdo.data.models.Location;
import com.obdo.data.models.Pin;
import com.obdo.data.models.Post;
import com.obdo.data.models.ReadPost;
import com.obdo.data.models.User;

import java.io.IOException;
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
    private Dao<Asset, String> assetDao = null;
    /**
     * Comment DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoComments
     * @see com.obdo.data.models.Comment
     */
    private Dao<Comment, String> commentDao = null;
    /**
     * Location DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoLocations
     * @see com.obdo.data.models.Location
     */
    private Dao<Location, String> locationDao = null;
    /**
     * Pin DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoPins
     * @see com.obdo.data.models.Pin
     */
    private Dao<Pin, String> pinDao = null;
    /**
     * Post DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoPosts
     * @see com.obdo.data.models.Post
     */
    private Dao<Post, String> postDao = null;
    /**
     * ReadPost DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoReadPosts
     * @see com.obdo.data.models.ReadPost
     */
    private Dao<ReadPost, String> readPostDao = null;
    /**
     * User DAO - ORMlite version
     * @since 12/22/2014
     * @see com.obdo.data.repos.RepoUsers
     * @see com.obdo.data.models.User
     */
    private Dao<User, String> userDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Post.class);
            TableUtils.createTable(connectionSource, Location.class);
            TableUtils.createTable(connectionSource, Comment.class);
            TableUtils.createTable(connectionSource, Asset.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Post.class, true);
            TableUtils.dropTable(connectionSource, Location.class, true);
            TableUtils.dropTable(connectionSource, Comment.class, true);
            TableUtils.dropTable(connectionSource, Asset.class, true);
            onCreate(db);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a link between the database and the User DAO object
     * @return User DAO object
     * @throws java.sql.SQLException
     */
    public Dao<User, String> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = DaoManager.createDao(getConnectionSource(), User.class);
        }
        return userDao;
    }

    /**
     * Create a link between the database and the Post DAO object
     * @return Post DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Post, String> getPostDao() throws SQLException {
        if (postDao == null) {
            postDao = DaoManager.createDao(getConnectionSource(), Post.class);
        }
        return postDao;
    }

    /**
     * Create a link between the database and the Location DAO object
     * @return Location DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Location, String> getLocationDao() throws SQLException {
        if (locationDao == null) {
            locationDao = DaoManager.createDao(getConnectionSource(), Location.class);
        }
        return locationDao;
    }

    /**
     * Create a link between the database and the Comment DAO object
     * @return Comment DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Comment, String> getCommentDAO() throws SQLException {
        if (commentDao == null) {
            commentDao = DaoManager.createDao(getConnectionSource(), Comment.class);
        }
        return commentDao;
    }

    /**
     * Create a link between the database and the Asset DAO object
     * @return Asset DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Asset, String> getAssetDAO() throws SQLException {
        if (assetDao == null) {
            assetDao = DaoManager.createDao(getConnectionSource(), Asset.class);
        }
        return assetDao;
    }

    /**
     * Create a link between the database and the Pin DAO object
     * @return Pin DAO object
     * @throws java.sql.SQLException
     */
    public Dao<Pin, String> getPinDao() throws SQLException {
        if (pinDao == null) {
            pinDao = DaoManager.createDao(getConnectionSource(), Pin.class);
        }
        return pinDao;
    }

    /**
     * Create a link between the database and the ReadPost DAO object
     * @return ReadPost DAO object
     * @throws java.sql.SQLException
     */
    public Dao<ReadPost, String> getReadPostDao() throws SQLException {
        if (readPostDao == null) {
            readPostDao = DaoManager.createDao(getConnectionSource(), ReadPost.class);
        }
        return readPostDao;
    }

    @Override
    public void close() {
        super.close();
        assetDao = null;
        commentDao = null;
        locationDao = null;
        pinDao = null;
        postDao = null;
        readPostDao = null;
        userDao = null;
    }
}
