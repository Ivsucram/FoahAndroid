package com.obdo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.obdo.data.models.Asset;
import com.obdo.data.models.Comment;
import com.obdo.data.models.Location;
import com.obdo.data.models.Pin;
import com.obdo.data.models.Post;
import com.obdo.data.models.ReadedPost;
import com.obdo.data.models.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, String> userDao = null;
    private Dao<Post, String> postDao = null;
    private Dao<Location, String> locationDao = null;
    private Dao<Comment, String> commentDao = null;
    private Dao<Asset, String> assetDao = null;
    private Dao<Pin, String> pinDao = null;
    private Dao<ReadedPost, String> readedPostDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        DatabaseInitializer initializer = new DatabaseInitializer(context);
        try {
            initializer.createDatabase();
            initializer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dao<User, String> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = DaoManager.createDao(getConnectionSource(), User.class);
        }
        return userDao;
    }

    public Dao<Post, String> getPostDao() throws SQLException {
        if (postDao == null) {
            postDao = DaoManager.createDao(getConnectionSource(), Post.class);
        }
        return postDao;
    }

    public Dao<Location, String> getLocationDao() throws SQLException {
        if (locationDao == null) {
            locationDao = DaoManager.createDao(getConnectionSource(), Location.class);
        }
        return locationDao;
    }

    public Dao<Comment, String> getCommentDao() throws SQLException {
        if (commentDao == null) {
            commentDao = DaoManager.createDao(getConnectionSource(), Comment.class);
        }
        return commentDao;
    }

    public Dao<Asset, String> getAssetDao() throws SQLException {
        if (assetDao == null) {
            assetDao = DaoManager.createDao(getConnectionSource(), Asset.class);
        }
        return assetDao;
    }

    public Dao<Pin, String> getPinDao() throws SQLException {
        if (pinDao == null) {
            pinDao = DaoManager.createDao(getConnectionSource(), Pin.class);
        }
        return pinDao;
    }

    public Dao<ReadedPost, String> getReadedPostDao() throws SQLException {
        if (readedPostDao == null) {
            readedPostDao = DaoManager.createDao(getConnectionSource(), ReadedPost.class);
        }
        return readedPostDao;
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

    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
