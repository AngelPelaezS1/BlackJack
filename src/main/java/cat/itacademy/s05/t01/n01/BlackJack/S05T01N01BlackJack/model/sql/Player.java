package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.PlayerType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("players")
public class Player {

    @Id
    private Long id;
    private String name;
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private PlayerType playerType;
    public Player(Long id) {
        this.id = id;
    }
    public Player() {

    }

    public Player(Long id, String name, int gamesWons, int gamesPlayed, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.gamesWon = gamesWon;
        this.gamesPlayed = getGamesPlayed();
        this.playerType = playerType;
    }

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void playGame(){
        this.gamesPlayed++;
    }

    public void winGame(){
        this.gamesWon++;
            System.out.println("winGame() llamado, gamesWon ahora es: " + this.gamesWon);
    }

}
