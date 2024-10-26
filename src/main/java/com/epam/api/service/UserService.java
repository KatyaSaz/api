package com.epam.api.service;

import com.epam.api.model.User;

import java.util.List;

public interface UserService {

    /**
     * Get users from multiple data sources
     *
     * @return combined list of all users
     */
    List<User> findAll();
}
