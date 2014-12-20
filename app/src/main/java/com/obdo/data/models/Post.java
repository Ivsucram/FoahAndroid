package com.obdo.data.models;

import android.content.res.AssetManager;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Post {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private String text;
    @DatabaseField(canBeNull = false)
    private ArrayList<String> visibility;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user = new User();
    @ForeignCollectionField(eager = false)
    private Location location = new Location();
    @ForeignCollectionField(eager = false)
    private Collection<Comment> comments = new ArrayList<Comment>();
    @ForeignCollectionField(eager = false)
    private Collection<Asset> assets = new ArrayList<Asset>();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ReadedPost readedPost = new ReadedPost();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Pin pin = new Pin();
}
