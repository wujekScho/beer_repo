package pl.wujekscho.beer.generic.exception;

public class IncorrectLoginException extends RuntimeException {
    public IncorrectLoginException() {
        super("Incorrect username or password.");
    }
}
