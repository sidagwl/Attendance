package com.example.siddhartha.attendance;

/**
 * Created by Siddhartha on 10/7/2017.
 */

public class Teacher {

private int id;
    private String name;
    private String subject;
    private String password;



    public int getId(){
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
