package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Long> {
    Flux<Player> findTop10ByOrderByGamesWonDesc();
    Mono<Player> findByName(String name);
}
