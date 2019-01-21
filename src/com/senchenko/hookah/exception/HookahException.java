package com.senchenko.hookah.exception;

public class HookahException extends Exception{
    public HookahException() {
    }

    public HookahException(String message) {
        super(message);
    }

    public HookahException(String message, Throwable cause) {
        super(message, cause);
    }

    public HookahException(Throwable cause) {
        super(cause);
    }
}
