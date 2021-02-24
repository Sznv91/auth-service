package ru.atom.test.task.auth.util;

public class UserFieldException extends RuntimeException {

    public static UserFieldException get(String message){
        return new UserFieldException(message);
    }

    private UserFieldException(String message) {
        super(message);
    }
}
