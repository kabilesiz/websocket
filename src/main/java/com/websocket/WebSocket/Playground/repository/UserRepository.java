package com.websocket.WebSocket.Playground.repository;


import com.websocket.WebSocket.Playground.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void SetUsers(){
        var hsyn = new User(1, "Huseyin");
        var btr = new User(2, "Batur");
        var frkn = new User(3, "Furkan");
        var gk = new User(4, "Gokmen");
        users.add(hsyn);
        users.add(btr);
        users.add(frkn);
        users.add(gk);
    }
    public List<User> getUsers(){
        return users;
    }

    public User getUserById(int id){
        for(var user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User getUserByName(String name){
        for(var user : users){
            if (user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }
}
