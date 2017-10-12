package com.example.rynel.github.model;

/**
 * Created by rynel on 10/12/2017.
 */

//Sends to MyResponse
public class Repo {

    String name, createdAt, description;

    public Repo(String name, String createdAt, String description) {
        this.name = name;
        this.createdAt = createdAt;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}