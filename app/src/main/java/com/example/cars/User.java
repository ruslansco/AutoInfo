package com.example.cars;

/**
 * Created by nulrybekkarshyga on 06.03.18.
 * Modified by Ruslan Shakirov.
 */

public class User {

    private String user_id;
    private String name;
    private String email;
    private Integer age;

    public User(String user_id, String name,String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User() {
    }


    public String getUser_id() {

        return user_id;
    }

    public void setUser_id(String user_id) {

        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
    public Integer getAge(){
        return age;
    }
    public Integer setAge(Integer age){
        return this.age;
    }
    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
