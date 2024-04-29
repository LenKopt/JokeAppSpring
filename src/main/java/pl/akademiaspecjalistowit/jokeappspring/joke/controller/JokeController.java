package pl.akademiaspecjalistowit.jokeappspring.joke.controller;

import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeService;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeEntityRepository;

import java.awt.print.Book;

@RestController
@RequestMapping("/jokes")
public class JokeController {
    private JokeService jokeService;
    //private final JokeEntityRepository jokeEntityRepository;

    public JokeController(JokeService jokeService) {

        this.jokeService = jokeService;
        //this.jokeEntityRepository = jokeEntityRepository;
    }

    @GetMapping("/joke")
    public Joke getAnyJoke(@RequestParam(name = "category", required = false) String category) {
        if (category != null) {
            return jokeService.getJoke(category);
        }
        return jokeService.getJoke();
    }

    @PostMapping
    public void addJoke(@RequestBody Joke joke) {
        jokeService.save(joke);
    }
}
