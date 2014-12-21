package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/22/2014.
 */
public class Visibility {
    @DatabaseField(generatedId = true)
    private String id;
    @DatabaseField(foreign = true)
    private User user;
    @DatabaseField(foreign = true)
    private Post post;

    Visibility() {}

    public Visibility(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
