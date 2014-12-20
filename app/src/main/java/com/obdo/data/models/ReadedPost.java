package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class ReadedPost {
    @DatabaseField(id = true)
    private String id;
    @ForeignCollectionField(eager = false)
    private Post post = new Post();
    @ForeignCollectionField(eager = false)
    private Comment lastComment = new Comment();
}
