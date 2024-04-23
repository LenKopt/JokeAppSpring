package pl.akademiaspecjalistowit.jokeappspring.joke.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeServiceException;

@ControllerAdvice
public class JokeControllerAdvice {
    @ExceptionHandler({JokeServiceException.class})
    public ResponseEntity<ErrorResponse> handleJokeServiceException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @Getter
    @AllArgsConstructor
    class ErrorResponse {
        private String reason;
    }
}