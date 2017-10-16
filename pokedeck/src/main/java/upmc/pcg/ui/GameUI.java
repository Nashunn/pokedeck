// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.ui;

import java.util.*;
import upmc.pcg.game.Game;

/*
 * GameUI
 */
public class GameUI {
    private final Game game = new Game();
    private final Scanner console = new Scanner(System.in);

    public void start() {
        print_welcome_msg();
        ArrayList<String> names = ask_players_names();
        game.initialize(names);
        game.play();
    }

    private ArrayList<String> ask_players_names() {
        return new ArrayList<>();
    }

    private void print_welcome_msg() {
        //todo
    }
}
