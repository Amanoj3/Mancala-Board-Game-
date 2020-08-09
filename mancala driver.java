import java.util.Scanner;

public class driver {
    public static void main(String[] args) {
        Mancala game = new Mancala();
        game.showBoard();
        game.showCommands();
        game.processCommands();
    }
}
