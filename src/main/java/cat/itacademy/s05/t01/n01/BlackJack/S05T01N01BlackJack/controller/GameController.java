package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.controller;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo.Game;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;

//implementar comentarios de swagger(annotaciones y tal)


@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody Player player) {
        return gameService.createGame(player.getName())
                .map(gameCreated -> ResponseEntity.status(HttpStatus.CREATED).body(gameCreated));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Game>> descriptionGame(@PathVariable String id){
        return gameService.getOne(id)
                .map(gameDescription -> ResponseEntity.status(HttpStatus.OK).body(gameDescription));
    }

    @DeleteMapping("/{id}/delete")
    public Mono<ResponseEntity<Game>> deleteGame(@PathVariable String id){
        return gameService.delete(id)
                .map(gameDelete -> ResponseEntity.noContent().build());
    }

    @PostMapping("/{id}/hit")
    public Mono<ResponseEntity<Game>> playHit(@PathVariable String id) {
        return gameService.getOne(id)
                .flatMap(gameService::hit)
                .map(updatedGame -> ResponseEntity.ok(updatedGame));
    }

    @PostMapping("/{id}/stand")
    public Mono<ResponseEntity<Game>> playStand(@PathVariable String id) {
        return gameService.getOne(id)
                .flatMap(gameService::stand)
                .map(updatedGame -> ResponseEntity.ok(updatedGame));
    }
}
