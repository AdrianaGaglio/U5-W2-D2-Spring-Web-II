package epicode.it.blog.exceptions;

public class AlreadyExistException extends Exception{
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
