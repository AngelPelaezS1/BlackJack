package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.controller;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

@Operation(
        summary = "Create new player.",
        description = "Create new player.",
        responses = {
                @ApiResponse(responseCode = "201", description = "Player created."),
                @ApiResponse(responseCode = "400", description = "Invalid data.")
        }
)
    @PostMapping("/new")
    Mono<ResponseEntity<Player>> createPlayer(@RequestBody Player player){
        return playerService.createPlayer(player.getName())
                .map(playerCreated -> ResponseEntity.status(HttpStatus.CREATED).body(playerCreated));
    }

@Operation(
        summary = "Update player name.",
        description = "Update player name by ID.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Player update."),
                @ApiResponse(responseCode = "404", description = "Player not found.")
        }
)
    @PutMapping("/{id}")
    Mono<ResponseEntity<Player>> updatePlayer(@PathVariable Long id, @RequestBody Player player){
        return playerService.update(id, player.getName())
                .map(playerUpdate -> ResponseEntity.status(HttpStatus.OK).body(playerUpdate));
    }

@Operation(
        summary = "Get players ranking.",
        description = "Get a ranking of the top 10 players.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Ranking obtained")
        }
)
    @GetMapping("/ranking")
    Flux<Player> getRanking(){
        return playerService.ranking();
    }
}
