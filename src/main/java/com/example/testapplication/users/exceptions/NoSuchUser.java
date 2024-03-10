package com.example.testapplication.users.exceptions;

public class NoSuchUser extends RuntimeException{
    @Override
    public String getMessage() {
        return "No such user";
    }
}
