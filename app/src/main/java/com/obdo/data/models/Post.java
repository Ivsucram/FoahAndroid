package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Post {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    public String text;
    @DatabaseField(canBeNull = false)
    public ArrayList<String> visibility;
}
