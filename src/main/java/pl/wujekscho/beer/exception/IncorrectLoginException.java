package pl.wujekscho.beer.exception;

public class IncorrectLoginException extends RuntimeException {
    public IncorrectLoginException() {
        super("Incorrect username or password.");
    }
}
