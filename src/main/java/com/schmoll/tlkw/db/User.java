package com.schmoll.tlkw.db;

public class User {
    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setRole(String role) {
        this.role = role;
    }
}
