package in.arvind.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception){
        // Log the exception here
        // You can customize the response based on the exception type
        // For example, if it's a NoResourceFoundException, return a 404 status code
        // If it's a DatabaseException, return a 500 status code
        // etc.
        System.out.println("An error occurred: " + exception.getMessage());
    }
}
