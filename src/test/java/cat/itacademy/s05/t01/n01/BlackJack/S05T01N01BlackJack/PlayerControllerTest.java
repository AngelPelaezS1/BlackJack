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
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

@WebFluxTest
public class PlayerControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
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


}
