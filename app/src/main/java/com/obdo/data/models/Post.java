package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.obdo.data.repos.Repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Post {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private String text;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user = new User();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ReadPost readPost = new ReadPost();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Pin pin = new Pin();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Location location = new Location();
    @ForeignCollectionField(eager = false)
    private Collection<Comment> comments = new ArrayList<Comment>();
    @ForeignCollectionField(eager = false)
    private Collection<Asset> assets = new ArrayList<Asset>();

    public List<Asset> getAssets(Repo repo) {
        return repo.Assets.getByPost(this);
    }

    public List<Comment> getComments(Repo repo) {
        return repo.Comments.getByPost(this);
    }
}
