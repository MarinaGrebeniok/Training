package com.training.exception;

public class CommonException extends RuntimeException {
    public CommonException(String message){
        super(message);
    }

    public CommonException(String message, Throwable t){
        super(message, t);
    }
}
