package com.example.rynel.github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
