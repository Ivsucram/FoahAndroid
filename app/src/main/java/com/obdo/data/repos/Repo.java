package com.obdo.data.repos;

import android.content.Context;

import com.obdo.data.DatabaseHelper;
import com.obdo.data.DatabaseManager;

/**
 * Repository (database) controller
 * How to use it:
 * Repo repo = new Repo(Context);
 * Model model = new Model("field1", "field2");
 * model.save(repo);
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
     * SQLite database open helper. Manage when the application needs to create or upgrade its database.
     * @since 12/22/2014
     * @see com.obdo.data.DatabaseHelper
     */
    private DatabaseHelper db;
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

    public Repo(Context context) {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);

        Assets = new RepoAssets(db);
        Comments = new RepoComments(db);
        Locations = new RepoLocations(db);
        Pins = new RepoPins(db);
        Posts = new RepoPosts(db);
        ReadPosts = new RepoReadPosts(db);
        Users = new RepoUsers(db);
    }
}
