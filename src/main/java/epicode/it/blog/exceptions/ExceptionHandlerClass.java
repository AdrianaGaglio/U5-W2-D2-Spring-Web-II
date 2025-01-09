package epicode.it.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.swing.text.html.parser.Entity;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AlreadyExistException.class)
    protected ResponseEntity<Object> alreadyExistException(AlreadyExistException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RequiredException.class)
    protected ResponseEntity<Object> requiredException(RequiredException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
