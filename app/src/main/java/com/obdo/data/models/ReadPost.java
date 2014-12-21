package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class ReadPost {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Comment lastComment = new Comment();
}
