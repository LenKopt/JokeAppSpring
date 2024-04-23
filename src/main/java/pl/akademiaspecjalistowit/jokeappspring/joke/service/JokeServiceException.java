package pl.akademiaspecjalistowit.jokeappspring.joke.service;

public class JokeServiceException extends RuntimeException{
    public JokeServiceException(String message) {
        super(message);
    }
}
