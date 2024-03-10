package com.example.testapplication.cars.exceptions;

public class NoSuchCar extends RuntimeException{
    @Override
    public String getMessage() {
        return "No such car";
    }
}
