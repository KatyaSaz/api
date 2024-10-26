package com.epam.api.service;


import com.epam.api.model.User;
import com.epam.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepository;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
