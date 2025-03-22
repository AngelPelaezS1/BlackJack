package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.PlayerType;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository.PlayerRepository;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private PlayerService playerService;

    @Test
    void testUpdatePlayerName(){
        long id = 1L;
        String oldName = "Cristian";
        String newName = "Andres";

        Player oldPlayer = new Player("Cristian", PlayerType.PLAYER);
        oldPlayer.setId(id);
        Player newPlayer = new Player("Andres", PlayerType.PLAYER);
        newPlayer.setId(id);

        Mockito.when(playerRepository.findById(id)).thenReturn(Mono.just(oldPlayer));
        Mockito.when(playerRepository.save(Mockito.any(Player.class))).thenReturn(Mono.just(newPlayer));
        Mono<Player> result = playerService.update(id, newName);

        StepVerifier.create(result)
                .expectNextMatches(player -> player.getName().equals("Andres"))
                .verifyComplete();





    }
}
