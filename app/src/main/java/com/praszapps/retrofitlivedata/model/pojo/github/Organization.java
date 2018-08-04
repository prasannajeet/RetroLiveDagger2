package com.praszapps.retrofitlivedata.model.pojo.github;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Organization {
    private String login;
    private int id;
    private String avatarurl;
    private String description;

    public Organization(String login, int id, String avatarurl, String description) {
        this.login = login;
        this.id = id;
        this.avatarurl = avatarurl;
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
        return avatarurl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarurl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("login", login).append("avatarUrl", avatarurl).append("description", description).toString();
    }
}
