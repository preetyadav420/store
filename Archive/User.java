package com.preet.store;

public class User {
    static long count = 0L;
    long id;
    String email;
    String password;
    String name;


    public User(String email, String password, String name){
        this.id = count++;
        this.email = email;
        this.password  = password;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


}
