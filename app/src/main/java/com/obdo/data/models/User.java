package com.obdo.data.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ivsucram on 12/20/2014.
 */
public class User {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField(canBeNull = false)
    private String phoneNumber;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = true)
    private String picture;

    public User() {}
    public User(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String phoneNumber, String name, String picture) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.picture = picture;
    }

    public String toString() {
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
