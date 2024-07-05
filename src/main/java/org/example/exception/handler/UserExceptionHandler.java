package org.example.exception.handler;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * "Exception handler" for controllers
 * Provides methods for handling various types of exceptions and returning appropriate views.
 */
//@Slf4j
@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(Exception e) {
        return "RuntimeException_error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Exception e) {
        return "NullPointerException_error";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(Exception e) {
        return "IOException_error";
    }

    @ExceptionHandler(ServletException.class)
    public String handleServletException(Exception e) {
        return "ServletException_error";
    }
}
