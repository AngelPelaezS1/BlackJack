package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.PlayerType;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

@WebFluxTest
public class PlayerControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private PlayerService playerService;

    @Test
    void testUpdatePlayerName() {
        long id = 1L;
        String oldName = "Cristian";
        String newName = "Andres";

        Player oldPlayer = new Player(oldName, PlayerType.PLAYER);
        oldPlayer.setId(id);
        Player newPlayer = new Player(newName, PlayerType.PLAYER);
        newPlayer.setId(id);

        when(playerService.update(id, newName)).thenReturn(Mono.just(newPlayer));

        webTestClient.put()
                .uri("/player/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newPlayer)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Andres")
                .jsonPath("$.id").isEqualTo(id);

    }

    @Test
    void testCreatePlayer() {
        Player player = new Player("Jofre", PlayerType.PLAYER);

        when(playerService.createPlayer("Jofre")).thenReturn(Mono.just(player));

        webTestClient.post()
                .uri("/player/new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(player)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Jofre");
    }

    @Test
    void testRanking() {
        Player player = new Player("Jofre", PlayerType.PLAYER);
        Player player1 = new Player("Antonio", PlayerType.PLAYER);
        Player player2 = new Player("Aurelian", PlayerType.PLAYER);
        Player player3 = new Player("Ezequiel", PlayerType.PLAYER);

        player.setGamesWon(8);
        player1.setGamesWon(4);
        player2.setGamesWon(10);
        player3.setGamesWon(7);

        when(playerService.ranking()).thenReturn(Flux.just(player2, player, player3, player1));

        webTestClient.get()
                .uri("/player/ranking")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Aurelian")
                .jsonPath("$[1].name").isEqualTo("Jofre")
                .jsonPath("$[2].name").isEqualTo("Ezequiel")
                .jsonPath("$[3].name").isEqualTo("Antonio");
    }
}