package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Comment {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = true)
    private String text;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user = new User();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
    @ForeignCollectionField(eager = false)
    private Collection<Asset> assets = new ArrayList<Asset>();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ReadedPost readedPost = new ReadedPost();
}
