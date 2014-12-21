package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/22/2014.
 */
public class Friend {
    @DatabaseField(generatedId = true)
    private String id;
    @DatabaseField(foreign = true)
    private User userA;
    @DatabaseField(foreign = true)
    private User userB;

    Friend() {}

    public Friend(User userA, User userB) {
        this.userA = userA;
        this.userB = userB;
    }
}
