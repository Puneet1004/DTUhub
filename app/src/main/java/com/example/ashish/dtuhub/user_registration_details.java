package com.example.ashish.dtuhub;

public class user_registration_details {
    String name;
    String year;
    String email;
    String password;

    public user_registration_details() {

    }

    public user_registration_details(String name, String year, String email) {
        this.name = name;
        this.year = year;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }


}
