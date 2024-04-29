package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.mapper.JokeMapper;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeEntityRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeRepository;

import java.util.List;
import java.util.Random;

@Service
public class JokeDataProvider implements JokeProvider {

    private final List<JokeRepository> jokeRepositories;
    private final JokeEntityRepository jokeEntityRepository;
    private final JokeMapper jokeMapper;
    private static long counter = 0;
    private final Random rand;

    public JokeDataProvider(List<JokeRepository> jokeRepositories, Random random, JokeEntityRepository jokeEntityRepository, JokeMapper jokeMapper) {

        this.jokeRepositories = jokeRepositories;
        this.rand = random;
        this.jokeEntityRepository = jokeEntityRepository;
        this.jokeMapper = jokeMapper;
    }

    @Override
    public Joke getJoke() {
        if (counter % 2 == 0) {
            List<JokeEntity> listJokes = jokeEntityRepository.findAll();
            JokeEntity anyJokeEntity = listJokes.get(rand.nextInt(listJokes.size()));
            counter++;
            return jokeMapper.fromEntity(anyJokeEntity);
        }
        List<Joke> anyJokes = getJokeRepository().getAllJokes();
        return anyJokes.get(rand.nextInt(anyJokes.size()));
    }

    @Override
    public Joke getJokeByCategory(String category) {
        if (counter % 2 == 0) {
            try {
                List<JokeEntity> listJokes = jokeEntityRepository.findAllByCategory(category);
                JokeEntity anyJokeEntity = listJokes.get(rand.nextInt(listJokes.size()));
                counter++;
                return jokeMapper.fromEntity(anyJokeEntity);
            } catch (JokeProviderExeption e) {
                throw new JokeProviderExeption("Joke from category " + category + " was not found");
            }
        }
        List<Joke> jokesByCategory = getJokeRepository().getAllByCategory(category);
        try {
            return jokesByCategory.get(rand.nextInt(jokesByCategory.size()));
        } catch (JokeProviderExeption e) {
            throw new JokeProviderExeption("Joke from category " + category + " was not found");
        }
    }

    private JokeRepository getJokeRepository() {
        return jokeRepositories.get(rand.nextInt(jokeRepositories.size()));
    }

    public void save(Joke joke) {
        jokeEntityRepository.save(jokeMapper.toEntity(joke));
    }
}