package com.example.tunlin.exception;

public enum ErrorCode {

    USER_NOT_FOUND(400,"user not found"),
    EXCEPTION(999,"exception"),
    AUTHENTICATION(999,"authentication"),
    USER_NOT_EXIT(401,"user not exit"),

    PASSWORD_VALIDATE(401,"Password must be at least 8 characters")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
