package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.controller;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service.PlayerService;
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

    @PostMapping("/new")
    Mono<ResponseEntity<Player>> createPlayer(@RequestBody Player player){
        return playerService.createPlayer(player.getName())
                .map(playerCreated -> ResponseEntity.status(HttpStatus.CREATED).body(playerCreated));
    }

    @PutMapping("/update/{id}")
    Mono<ResponseEntity<Player>> updatePlayer(@PathVariable Long id, @RequestBody Player player){
        return playerService.update(id, player.getName())
                .map(playerUpdate -> ResponseEntity.status(HttpStatus.OK).body(playerUpdate));
    }

    @GetMapping("/ranking")
    Flux<Player> getRanking(){
        return playerService.ranking();
    }
}
