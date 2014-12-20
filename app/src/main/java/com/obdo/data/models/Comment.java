package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Comment {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = true)
    private String text;
}
