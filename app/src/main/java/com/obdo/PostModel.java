package com.obdo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by richard on 12/14/14.
 */
public class PostModel {

    private String id;
    private LatLng location;
    private String message;
    private UserModel user;
    private int x, y;

    public PostModel(String id, LatLng location, String message, UserModel user) {
        this.id = id;
        this.location = location;
        this.message = message;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
