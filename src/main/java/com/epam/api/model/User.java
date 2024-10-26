package com.epam.api.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String name;
    private String surname;

    public User(String id, String username, String name, String surname) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

}
