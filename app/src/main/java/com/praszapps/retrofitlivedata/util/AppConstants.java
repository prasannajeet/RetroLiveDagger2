package com.praszapps.retrofitlivedata.util;

public enum AppConstants {
    INSTANCE;

    public String getNewsBaseUrl() {
        return "https://newsapi.org/";
    }

    public String getGithubBaseUrl() {
        return "https://api.github.com";
    }
}
