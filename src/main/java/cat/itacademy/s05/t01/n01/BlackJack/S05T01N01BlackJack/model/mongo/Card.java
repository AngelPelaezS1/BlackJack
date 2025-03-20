package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.model.mongo;

import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.RankCard;
import cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums.SuitCard;

public class Card {

    private RankCard rankCard;
    private SuitCard suitCard;

    public Card(RankCard rankCard, SuitCard suitCard) {
        this.rankCard = rankCard;
        this.suitCard = suitCard;
    }

    public RankCard getRankCard() {
        return rankCard;
    }

    public SuitCard getSuitCard() {
        return suitCard;
    }

}

