package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class Pin {
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user = new User();
    @ForeignCollectionField(eager = false)
    private Collection<Post> posts = new ArrayList<Post>();
}
