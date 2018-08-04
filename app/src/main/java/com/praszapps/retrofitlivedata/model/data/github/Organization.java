package com.praszapps.retrofitlivedata.model.data.github;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("login", login).append("avatarUrl", avatarUrl).append("description", description).toString();
    }
}
