package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeServiceException;

public class JokeProviderExeption extends JokeServiceException {
    public JokeProviderExeption(String message) {
        super(message);
    }
}
