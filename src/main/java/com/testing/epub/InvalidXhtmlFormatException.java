package com.testing.epub;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class InvalidXhtmlFormatException extends RuntimeException {

    public InvalidXhtmlFormatException() {
    }

    public InvalidXhtmlFormatException(String message) {
        super(message);
    }

    public InvalidXhtmlFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidXhtmlFormatException(Throwable cause) {
        super(cause);
    }

    public InvalidXhtmlFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
