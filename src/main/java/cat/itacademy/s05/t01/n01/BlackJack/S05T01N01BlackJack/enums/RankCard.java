package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.enums;

public enum RankCard {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK (10), QUEEN(10), KING(10);

        private final int points;

        RankCard(int points){
            this.points=points;
        }
        public int getPoints() {
            return points;
        }
    }