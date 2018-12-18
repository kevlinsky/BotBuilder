package ru.kpfu.kevlinsky.exceptions;

public class ReactionException extends Exception{
    public ReactionException() {
    }

    public ReactionException(String message) {
        super(message);
    }

    public ReactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReactionException(Throwable cause) {
        super(cause);
    }
}
