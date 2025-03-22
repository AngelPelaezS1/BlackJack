package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.PlayerType;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.RankCard;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.SuitCard;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.sql.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    private String id;
    private Player player;
    private Player croupier;
    private boolean isFinished;
    private String winner;
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> croupierHand = new ArrayList<>();


    @Transient
    @JsonIgnore
    private List<Card> deck = new ArrayList<>();


    public Game(Player player) {
        this.player = player;
        this.croupier = new Player("Croupier", PlayerType.CROUPIER);
        this.deck = createDeck();
        this.playerHand = new ArrayList<>();
        this.croupierHand = new ArrayList<>();
        this.isFinished = false;
        this.winner = null;
    }

    public List<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        for (SuitCard suitCard : SuitCard.values()) {
            for (RankCard rankCard : RankCard.values()) {
                deck.add(new Card(rankCard, suitCard));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

    public int calculeTotal(List<Card> hand) {
        int total = hand.stream()
                .mapToInt(card -> card.getRankCard().getPoints())
                .sum();

        long aceCount = hand.stream()
                .filter(card -> card.getRankCard() == RankCard.ACE)
                .count();

        while(total > 21 && aceCount > 0){
            total -= 10;
            aceCount --;
        }
        return total;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getCroupier() {
        return croupier;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setFinished(boolean finished) {
        this.isFinished = finished;
    }

    public List<Card> getCroupierHand() {
        return croupierHand;
    }

    public void setCroupierHand(List<Card> croupierHand) {
        this.croupierHand = croupierHand;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}