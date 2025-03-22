package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.controller;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo.Game;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

@Operation(
        summary = "Create new game.",
        description = "Create a new game for a new or existing player.",
        responses = {
                @ApiResponse(responseCode = "201", description = "Game created."),
                @ApiResponse(responseCode = "400", description = "Invalid data.")
        }
)
    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody Player player) {
        return gameService.createGame(player.getName())
                .map(gameCreated -> ResponseEntity.status(HttpStatus.CREATED).body(gameCreated));
    }

@Operation(
        summary = "Get details of a game.",
        description = "Get details of a game by ID.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Game found."),
                @ApiResponse(responseCode = "404", description = "Game not found.")
        }
)
    @GetMapping("{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String id){
        return gameService.getOne(id)
                .map(gameDescription -> ResponseEntity.status(HttpStatus.OK).body(gameDescription));
    }

@Operation(
        summary = "Delete game.",
        description = "Delete game by ID.",
        responses = {
                @ApiResponse(responseCode = "204", description = "Game deleted."),
                @ApiResponse(responseCode = "404", description = "Game not found.")
        }
)
    @DeleteMapping("/{id}/delete")
    public Mono<ResponseEntity<Game>> deleteGame(@PathVariable String id){
        return gameService.delete(id)
                .map(gameDelete -> ResponseEntity.noContent().build());
    }

@Operation(
        summary = "Make a play (hit).",
        description = "Make a play (hit), by ID.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Play made."),
                @ApiResponse(responseCode = "404", description = "Game not found.")
        }
)
    @PostMapping("/{id}/hit")
    public Mono<ResponseEntity<Game>> playHit(@PathVariable String id) {
        return gameService.getOne(id)
                .flatMap(gameService::hit)
                .map(updatedGame -> ResponseEntity.ok(updatedGame));
    }

@Operation(
        summary = "Make a play (stand).",
        description = "Make a play (stand), by ID",
        responses = {
                @ApiResponse(responseCode = "200", description = "Play made."),
                @ApiResponse(responseCode = "404", description = "Game not found.")
        }
)
    @PostMapping("/{id}/stand")
    public Mono<ResponseEntity<Game>> playStand(@PathVariable String id) {
        return gameService.getOne(id)
                .flatMap(gameService::stand)
                .map(updatedGame -> ResponseEntity.ok(updatedGame));
    }
}
