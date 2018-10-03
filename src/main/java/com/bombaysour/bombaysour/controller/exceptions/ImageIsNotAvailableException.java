package com.bombaysour.bombaysour.controller.exceptions;

public class ImageIsNotAvailableException extends RuntimeException{

    private static String info = "Exception:";
    private String message;

    public ImageIsNotAvailableException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
