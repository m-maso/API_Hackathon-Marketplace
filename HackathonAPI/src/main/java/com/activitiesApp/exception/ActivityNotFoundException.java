package com.activitiesApp.exception;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(String msg)
    {
        super(msg);
    }
}
