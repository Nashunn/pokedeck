// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.util.*;
import upmc.pcg.ui.CardMenuUI;
import upmc.pcg.ui.MenuUI;

/*
 * Game contains all the methods to run the game 
 * @author Nicolas & Gabin
 */
public class Game {
    private HashMap<String, Collection> collections = new HashMap<String, Collection>();
    private ArrayList<String> players_name;
    private String currentPlayer = "Unknown";
    private Collection currentCollection;
    private boolean boolQuitGame = false;
    
    /**
     * Default constructor
     */
    public Game() {}
    
    /**
     * Initialize attributes to begin the game
     * Note : For this version, only the first player can have a collection and play
     */
    public void initialize(ArrayList<String> par_players_name) {
        players_name = par_players_name;
        currentPlayer = players_name.get(0);
        
        Collection collectionPlayer = new Collection(currentPlayer);
        collections.put(currentPlayer, collectionPlayer);
        currentCollection = collections.get(currentPlayer);
        CardMenuUI.setActualCollection(currentCollection);
    }
    
    /**
     * Main method for the game
     */
    public void play() {
        int collectionMenuChoice = -1;
        
        while(!boolQuitGame) {
            collectionMenuChoice = MenuUI.collection_main_menu(currentPlayer);
            switch_collection_main_menu_choice(collectionMenuChoice);
        }
    }
    
    /**
     * Does actions base on the choice made by the player in the collection main menu
     */
    private void switch_collection_main_menu_choice(int menuChoice) {
        switch(menuChoice) {
            //Add a card
            case 1:
                currentCollection.add_card(MenuUI.add_card_menu());
                break;
            //Consult collection
            case 2:
                MenuUI.action_consult_menu(currentCollection);
                break;
            //Search cards by criteria
            case 3:
                System.out.println("TODO");
                break;
            //Quit the game
            case 4:
                boolQuitGame = true;
                break;
            //Default
            default:
                System.out.println("(!) Invalid choice");
                break;
        }
    }
}
