package epicode.it.blog.exceptions;

public class RequiredException extends RuntimeException {

    public RequiredException() {
        super();
    }

    public RequiredException(String message) {
        super(message);
    }
}
