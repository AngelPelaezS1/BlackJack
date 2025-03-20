package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException (String message){
        super(message);
    }
}
