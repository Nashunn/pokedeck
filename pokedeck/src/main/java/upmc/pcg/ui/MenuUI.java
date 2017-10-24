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
import upmc.pcg.game.EnergyCard;
import upmc.pcg.game.PokemonCard;
import upmc.pcg.game.TrainerCard;

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
    private static void print_collection_menu_msg(String playerName) {
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
    private static int ask_collection_menu() {
        int choice = -1;
        
        System.out.println("\nYour choice ? ");
        choice = console.nextInt();
        
        return choice;
    }
    
    /**
     * Display the creation card menu
     * return the created card
     */
    public static Card add_card_menu() {
        Card createdCard = null;
        
        //ask what type of card
        createdCard = ask_type_card();
        //run create() method from card
        createdCard.create();
        
        return createdCard;
    }
    
    /**
     * Ask the type of the card and return a card of this type (pokemon, trainer, energy)
     */
    private static Card ask_type_card() {
        String chosenType = "";
        Card card = null;
        
        chosenType = print_type_card_msg();
        card = switch_card_type(chosenType);
        
        return card;
    }
    
    /**
     * Print the message at the beginning of the menu to add a card
     * return the chosen type by the player
     */
    private static String print_type_card_msg() {
        String choice = "";
        
        System.out.println("****************************");
        System.out.println("Card creation :");
        choice = print_option_type_card();
        
        if(choice == null)
            choice = print_type_card_msg();
        
        return choice;
    }
    
    /**
     * Print the 3 types of card for the creation card menu
     * return the choice of the player
     */
    private static String print_option_type_card() {
        String tabTypes[] = Card.CARD_TYPES;
        int choice = 0;
        
        System.out.println("What type of card do you want ? ");
        for(int i=0; i<tabTypes.length; i++) {
            System.out.println(" "+(i+1)+". "+tabTypes[i]);
        }
        
        while(choice == 0 && !(choice >= 1 && choice <= tabTypes.length)) {
            System.out.println("\nYour choice ? ");
            choice = console.nextInt();
        }
        
        return tabTypes[choice-1];
    }
    
    /**
     * Create a card following the given type
     */
    private static Card switch_card_type(String chosenType) {
        Card card = null;
        
        switch(chosenType) {
            case "pokemon":
                card = new PokemonCard();
                break;
            case "trainer":
                card = new TrainerCard();
                break;
            case "energy":
                card = new EnergyCard();
                break;
            default:
                System.out.println("(!) Unknown card type");
                break;
        }
        
        return card;
    }

    
    /**
     * Display the content of the collection and ask the user which card he wants to consult
     * return the chosen card
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
    private static Card card_consult_menu(Collection collection) {
        int chosenIndex = 1;
        boolean boolIndexOk = false;
        
        while(!boolIndexOk) {
            System.out.println("Select a card : ");
            chosenIndex = console.nextInt()-1;
            
            if(chosenIndex>=0 && chosenIndex < collection.get_size())
                boolIndexOk = true;
            else
                System.out.println("(!) This card isn't in the collection");
        }
        
        return collection.get_card(chosenIndex);
    }
}
