package com.websocket.WebSocket.Playground.controllers;

import com.websocket.WebSocket.Playground.entity.User;
import com.websocket.WebSocket.Playground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("hello")
    public String getHello(){
        return "Hello";
    }

    @GetMapping("users")
    public List<User> getUsers(){
        return userRepository.getUsers();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable int id){
        return userRepository.getUserById(id);
    }

    @GetMapping("user")
    public User getUserByName(@RequestParam String name){
        return userRepository.getUserByName(name);
    }
}
