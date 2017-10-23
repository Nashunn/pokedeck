// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.ui;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import upmc.pcg.game.Card;
import upmc.pcg.game.Collection;

/**
 * MenuUI contains statics methods that display menu in the game to interract with the user
 * @author Nicolas & Gabin
 */
public final class MenuUI {
    private static final Scanner console = new Scanner(System.in);
    
    //Private constructor
    private MenuUI() {}
    
    /**
     * Display the main choice menu that ask what the player want to do with his collection
     */
    public static int collection_main_menu(String playerName) {
        int choiceMenu = -1;
        
        print_collection_menu_msg(playerName);
        while(choiceMenu == -1 && !(choiceMenu>=1 && choiceMenu <=4))
            choiceMenu = ask_collection_menu();
        
        return choiceMenu;
    }
    
    /**
     * Print messages at the beginning of the collection main menu
     */
    public static void print_collection_menu_msg(String playerName) {
        System.out.println("****************************");
        System.out.println("* "+playerName+"'s Collection");
        System.out.println("****************************");
        System.out.println("What do you want to do with your collection ?");
        System.out.println(" 1. Add a card");
        System.out.println(" 2. Consult");
        System.out.println(" 3. Search cards by criteria");
        System.out.println(" 4. Quit the game");
    }
    
    /**
     * Ask the player what choice he want to do in the collection main menu
     */
    public static int ask_collection_menu() {
        int choice = -1;
        
        System.out.println("\nYour choice ? ");
        choice = console.nextInt();
        
        return choice;
    }
    
    /**
     * Display the content of the collection and ask the user which card he wants to consult
     */
    public static Card collection_consult_menu(Collection collection) {
        Card chosenCard = null;
        String userConsultCard = "";      
        
        System.out.println("****************************");
        System.out.println("Collection content :\n");
        collection.list_all_cards();
        
        console.nextLine(); //Empty buffer
        while(userConsultCard.equals("") || (!userConsultCard.equals("n") && !userConsultCard.equals("y"))) {
            System.out.println("Do you want to consult a card ? (y/n)");
            userConsultCard = console.nextLine();
        }
        
        if(userConsultCard.equals("y")) {
            if(collection.get_size() != 0)
                chosenCard = card_consult_menu(collection);
            else
                 System.out.println("(!) You don't have cards in your collection yet");
        }
        
        return chosenCard;
    }    
    
    /**
     * Return the card selected by the user in a menu
     */
    public static Card card_consult_menu(Collection collection) {
        int chosenIndex = 1;
        boolean boolIndexOk = false;
        
        while(!boolIndexOk) {
            System.out.println("Select a card : ");
            chosenIndex = console.nextInt();
            
            if(chosenIndex>0 && chosenIndex <= collection.get_size())
                boolIndexOk = true;
            else
                System.out.println("(!) This card isn't in the collection");
        }
        
        return collection.get_card(chosenIndex);
    }
}
