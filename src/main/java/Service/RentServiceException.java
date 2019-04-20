package Service;

public class RentServiceException extends RuntimeException {

    public RentServiceException(String message) {
        super("RentServiceException: " + message);
    }

}
