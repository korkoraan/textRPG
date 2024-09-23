import Environment.Environment;
import Game.Game;

public class Main {
    private static Environment environment;

    public static void main(String[] args) {
        var game = new Game();
        game.run();
    }
}