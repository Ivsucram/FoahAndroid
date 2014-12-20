package com.obdo.data.repos;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.obdo.data.DatabaseHelper;
import com.obdo.data.models.Post;
import com.obdo.data.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class RepoPosts {

    Dao<Post, String> postDao;

    public RepoPosts(DatabaseHelper db) {
        try {
            postDao = db.getPostDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Post post)
    {
        try {
            return postDao.create(post);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int update(Post post)
    {
        try {
            return postDao.update(post);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(Post post)
    {
        try {
            return postDao.delete(post);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    //TODO: Get Post by User - get List
    //TODO: Get Post by Location - get List
    public Post getById(String id)
    {
        try {
            QueryBuilder<Post, String> qb = postDao.queryBuilder();

            qb.where().eq("id", id);

            PreparedQuery<Post> pq = qb.prepare();
            return postDao.queryForFirst(pq);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> getAll()
    {
        try {
            return postDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
