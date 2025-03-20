package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo.Game;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
}
