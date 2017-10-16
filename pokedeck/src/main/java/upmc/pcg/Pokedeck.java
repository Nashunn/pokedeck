// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg;

import upmc.pcg.ui.GameUI;

/*
 * Pokedeck is the main class of the project, implementing the main method
 */
public class Pokedeck {
    public static void main(String[] args) {
        GameUI game_ui = new GameUI();
        game_ui.start();
    }
}
