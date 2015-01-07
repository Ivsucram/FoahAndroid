package com.obdo.data.repos;

import android.content.Context;

import com.obdo.data.DatabaseHelper;
import com.obdo.data.DatabaseManager;

/**
 * Repository (database) controller
 * How to use it:
 * Example 1 - One record manipulation
 * Repo repo = new Repo(Context);
 * Model model = new Model("field1", "field2");
 * model.save(repo);
 *
 * Example 2 - Several records manipulation
 * Repo repo = new Repo(Context);
 * repo.Users.getAll();
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/22/2014
 * @version 1.0
 * @see com.obdo.data.repos.RepoAssets
 * @see com.obdo.data.repos.RepoComments
 * @see com.obdo.data.repos.RepoLocations
 * @see com.obdo.data.repos.RepoPins
 * @see com.obdo.data.repos.RepoPosts
 * @see com.obdo.data.repos.RepoReadPosts
 * @see com.obdo.data.repos.RepoUsers
 */
public class Repo {
    /**
     *  Asset DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoAssets
     *  @see com.obdo.data.models.Asset
     */
    public RepoAssets Assets;
    /**
     *  Comment DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoComments
     *  @see com.obdo.data.models.Comment
     */
    public RepoComments Comments;
    /**
     *  Friend DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoFriends
     *  @see com.obdo.data.models.Friend
     */
    public RepoFriends Friends;
    /**
     *  Location DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoLocations
     *  @see com.obdo.data.models.Location
     */
    public RepoLocations Locations;
    /**
     *  Pin DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoPins
     *  @see com.obdo.data.models.Pin
     */
    public RepoPins Pins;
    /**
     *  Post DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoPosts
     *  @see com.obdo.data.models.Post
     */
    public RepoPosts Posts;
    /**
     *  ReadPost DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoReadPosts
     *  @see com.obdo.data.models.ReadPost
     */
    public RepoReadPosts ReadPosts;
    /**
     *  User DAO
     *  @since 12/22/2014
     *  @see com.obdo.data.repos.RepoUsers
     *  @see com.obdo.data.models.User
     */
    public RepoUsers Users;
    /**
     *  Visible DAO
     *  @since 12/22/2014
     *  @see RepoVisibles
     *  @see com.obdo.data.models.Visible
     */
    public RepoVisibles Visibles;

    public Repo(Context context) {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<>();
        DatabaseHelper db = manager.getHelper(context);

        Assets = new RepoAssets(db);
        Comments = new RepoComments(db);
        Friends = new RepoFriends(db);
        Locations = new RepoLocations(db);
        Pins = new RepoPins(db);
        Posts = new RepoPosts(db);
        ReadPosts = new RepoReadPosts(db);
        Users = new RepoUsers(db);
        Visibles = new RepoVisibles(db);
    }
}
