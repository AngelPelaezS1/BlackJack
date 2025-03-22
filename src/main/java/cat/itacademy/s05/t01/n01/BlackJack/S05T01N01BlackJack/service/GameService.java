package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.service;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.exception.GameNotFoundException;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo.Game;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository.GameRepository;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerService playerService, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    public Mono<Game> createGame(String playerName) {
        return playerRepository.findByName(playerName)
                .switchIfEmpty(playerService.createPlayer(playerName))
                .flatMap(player -> {
                    player.playGame();
                    return playerRepository.save(player)
                            .then(Mono.fromCallable(() -> {
                                Game game = new Game(player);
                                game.setDeck(game.createDeck());
                                deal(game);
                                return game;
                            }))
                            .flatMap(gameRepository::save);
                });
    }

    public void deal(Game game) {
        if (game.getDeck() == null || game.getDeck().isEmpty()) {
            throw new IllegalStateException("There are not cards in the deck.");
        }

        game.getPlayerHand().add(game.getDeck().get(0));
        game.getDeck().remove(0);
        game.getPlayerHand().add(game.getDeck().get(0));
        game.getDeck().remove(0);

        game.getCroupierHand().add(game.getDeck().get(0));
        game.getDeck().remove(0);
        game.getCroupierHand().add(game.getDeck().get(0));
        game.getDeck().remove(0);
    }

    public Mono<Game> hit(Game game){
        game.getPlayerHand().add(game.getDeck().get(0));
        game.getDeck().remove(0);

        int playerScore = game.calculeTotal(game.getPlayerHand());
        if(playerScore >= 21){
            game.getCroupierHand().add(game.getDeck().get(0));
            game.getDeck().remove(0);
            determineWinner(game);
        }
        return gameRepository.save(game);
    }

    public Mono<Game> stand(Game game){
        while(game.calculeTotal(game.getCroupierHand()) < 17) {
            game.getCroupierHand().add(game.getDeck().get(0));
            game.getDeck().remove(0);
        }
        determineWinner(game);
        return gameRepository.save(game);
    }

    public Mono<Game> playHit(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found.")))
                .flatMap(game -> hit(game));
    }

    public Mono<Game> playStand(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found.")))
                .flatMap(game -> stand(game));
    }

    public void determineWinner(Game game){
        int playerScore = game.calculeTotal(game.getPlayerHand());
        int croupierScore = game.calculeTotal(game.getCroupierHand());

        if(playerScore > 21){
            game.setWinner(game.getCroupier().getName());
        }else if(croupierScore > 21){
            game.setWinner(game.getPlayer().getName());
            game.getPlayer().winGame();
        }else if(playerScore > croupierScore){
            game.setWinner(game.getPlayer().getName());
            game.getPlayer().winGame();
        }else if(croupierScore > playerScore){
            game.setWinner(game.getCroupier().getName());
        }else{
            game.setWinner("Draw.");
            playerRepository.save(game.getPlayer()).subscribe();
        }
        playerRepository.findById(game.getPlayer().getId())
                .flatMap(player -> {
                    player.setGamesWon(game.getPlayer().getGamesWon());
                    return playerRepository.save(player);
                }).subscribe();


        game.setFinished(true);
        gameRepository.save(game).subscribe();
    }

    public Mono<Game> getOne(String id){
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found.")));
    }

    public Mono<Void> delete (String id){
        return gameRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found.")))
                .flatMap(game -> gameRepository.deleteById(id));
    }
}



