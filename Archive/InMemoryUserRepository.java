package com.preet.store;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InMemoryUserRepository implements UserRepository{

    HashMap<String,User> users;
    public InMemoryUserRepository()
    {
       users = new HashMap<String,User>();
    }

    public int save(User user){

        if(users.containsKey(user.email))
        {
            System.out.println("User already there");
            return -1;
        }
        else {
            users.put(user.email, user);
            System.out.println("User saved with email: " + user.email);
            return 1;
        }
    }
}
