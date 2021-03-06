package pl.wujekscho.beer.generic.exception;

public class NoDBResultException extends RuntimeException {
    public NoDBResultException(String message) {
        super(message);
    }
    public NoDBResultException() {
        super("No results found in database of given parameters.");
    }
}
