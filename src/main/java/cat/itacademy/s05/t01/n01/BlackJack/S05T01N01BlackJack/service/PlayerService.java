package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.PlayerType;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.exception.RankingEmptyException;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Mono<Player> createPlayer(String name){
        if(name.isEmpty()){
            return Mono.error(new IllegalArgumentException("Empty name."));
        }
        Player player = new Player(name, PlayerType.PLAYER);
        return playerRepository.save(player);
    }

    public Mono<Player> update(Long id, String newName) {
        return playerRepository.findById(id)
                .flatMap(player -> {
                    player.setName(newName);
                    return playerRepository.save(player);
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No player with that ID found.")));

    }

    public Flux<Player> ranking() {
        return playerRepository.findTop10ByOrderByGamesWonDesc()
                .switchIfEmpty(Flux.error(new RankingEmptyException("The ranking is empty.")));
    }
}
