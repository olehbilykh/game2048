package kp.game2048.service.exceptions;

public class CommentException extends Exception {

    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
