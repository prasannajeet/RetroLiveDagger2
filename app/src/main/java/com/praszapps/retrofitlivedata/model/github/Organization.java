package com.praszapps.retrofitlivedata.model.github;

public class Organization {
    private String login;
    private int id;
    private String avatarUrl;
    private String description;

    public Organization(String login, int id, String avatarUrl, String description) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
