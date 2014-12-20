package com.obdo.data.repos;

import android.content.Context;

import com.obdo.data.DatabaseHelper;
import com.obdo.data.DatabaseManager;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Repo {
    DatabaseHelper db;

    public RepoUsers Users;
    public RepoPosts Posts;
    public RepoLocations Location;

    public Repo(Context context) {
        DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
        db = manager.getHelper(context);

        Users = new RepoUsers(db);
        Posts = new RepoPosts(db);
        Location = new RepoLocations(db);
    }
}
