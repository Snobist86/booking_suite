package by.htp.Pankov.exception;

public class SuiteDaoException extends RuntimeException {

    public SuiteDaoException(String message) {
        super(message);
    }

    public SuiteDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuiteDaoException(Throwable cause) {
        super(cause);
    }
}
