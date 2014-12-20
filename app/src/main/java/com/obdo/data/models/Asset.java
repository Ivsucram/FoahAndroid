package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Asset {
    @DatabaseField(id = true)
    private String string;
    @DatabaseField(canBeNull = true)
    private String file;
}
