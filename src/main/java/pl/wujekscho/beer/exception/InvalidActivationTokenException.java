package pl.wujekscho.beer.exception;

public class InvalidActivationTokenException extends RuntimeException {
    private InvalidActivationTokenException() {
    }

    public InvalidActivationTokenException(String message) {
        super(message);
    }
}
