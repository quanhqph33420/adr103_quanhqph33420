package com.hq.quanhqph33420_lab1;

import java.util.HashMap;

public class UserModel {
    private String id;
    private String user;
    private String pass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public UserModel() {
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserModel(String id, String user, String pass) {
        this.id = id;
        this.user = user;
        this.pass = pass;
    }

    public HashMap<String, Object> createHashMap() {
        HashMap<String, Object> h = new HashMap<>();
        h.put("id", id);
        h.put("user", user);
        h.put("pass", pass);
        return h;
    }
}
