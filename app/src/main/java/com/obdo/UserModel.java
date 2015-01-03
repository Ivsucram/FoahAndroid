package com.obdo;

/**
 * Created by richard on 12/14/14.
 */
public class UserModel {

    private String id;
    private String name;
    private String profilePictureURL;

    public UserModel(String id, String name, String profilePictureURL) {
        this.id = id;
        this.name = name;
        this.profilePictureURL = profilePictureURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }
}
