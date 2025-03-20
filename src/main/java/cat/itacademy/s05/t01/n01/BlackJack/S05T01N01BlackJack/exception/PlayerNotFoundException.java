package cat.itacademy.s05.t01.n01.BlackJack.S05T01N01BlackJack.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException (String message){
        super(message);
    }
}
