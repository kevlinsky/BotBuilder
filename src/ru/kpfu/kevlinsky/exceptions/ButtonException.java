package ru.kpfu.kevlinsky.exceptions;

public class ButtonException extends Exception{
    public ButtonException() {
    }

    public ButtonException(String message) {
        super(message);
    }

    public ButtonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ButtonException(Throwable cause) {
        super(cause);
    }
}
