package com.epam.api.controller;

import com.epam.api.model.User;
import com.epam.api.service.UserDataSourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users API", description = "API to fetch users data from multiple databases")
public class UserController {

    private UserDataSourceService userService;

    @Autowired
    public UserController(UserDataSourceService userService){
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsersFromAllSources();
        return ResponseEntity.ok(users);
    }
}


