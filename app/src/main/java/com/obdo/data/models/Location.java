package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Location {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private float latitude;
    @DatabaseField(canBeNull = false)
    private float longitude;
    @DatabaseField(canBeNull = true)
    private float radius;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post = new Post();
}
