package pl.wujekscho.beer.generic.exception;

public class InvalidActivationTokenException extends RuntimeException {
    private InvalidActivationTokenException() {
    }

    public InvalidActivationTokenException(String message) {
        super(message);
    }
}
