package pl.wujekscho.beer.generic.exception;

public class NotActivatedUserException extends RuntimeException {
    public NotActivatedUserException() {
        super("Users account is not activated");
    }
}
