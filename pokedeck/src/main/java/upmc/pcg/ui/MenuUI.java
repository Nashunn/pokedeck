// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.ui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
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
        int choice = 0;
        
        do {
            try {
                System.out.println("\nYour choice ?");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                console.nextLine();
            }
        }while(!(choice>=1 && choice <=4));
        
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
            try {
                System.out.println("\nYour choice ? ");
                choice = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                console.nextLine();
            }
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
            try {
                System.out.println("Select a card : ");
                chosenIndex = console.nextInt()-1;
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a card number !\n");
                console.nextLine();
            }
            
            if(chosenIndex>=0 && chosenIndex < collection.get_size())
                boolIndexOk = true;
            else
                System.out.println("(!) This card isn't in the collection");
        }
        
        return collection.get_card(chosenIndex);
    }
    
    /**
     * Display the messages at the beginning of the creation of a card step by step
     */
    public static void print_create_card_msg(String cardType) {
        System.out.println("****************************");
        System.out.println("Create your "+cardType+" card :\n");
        System.out.println("Prof. Oak : Here you can create your card step by step ! Let's Go !");
    }
    
    /**
     * Ask the player to fill a value for each attributes (without some exception) and return an array with every value
     */
    public static ArrayList ask_card_attributes_values(HashMap<String, Class> cardAttributes) {
        ArrayList resultArray = new ArrayList();
        String currentAttr = "";
        Class currentAttrType = null; 
        
        for(Map.Entry<String, Class> entry : cardAttributes.entrySet()){
            currentAttr = entry.getKey();
            currentAttrType = entry.getValue();
            
            //If it's not an exception
            if(currentAttrType.equals(String.class) || currentAttrType.equals(int.class) || currentAttrType.equals(boolean.class) || currentAttrType.equals(float.class))
                ask_single_attribute(currentAttr, currentAttrType);
        }
        
        return resultArray;
    }
    
    /**
     * Ask for one attribute with his name, and return the value in function of the type argument
     */
    private static Object ask_single_attribute(String name, Class type) {
        Object value = null;
        
        while(value == null || value == "") {
            try {
                System.out.println(" * "+name+" : ");
                if(type.equals(String.class)) {
                    console.nextLine();
                    value = console.nextLine();         
                } 
                else if(type.equals(int.class)) {
                    value = console.nextInt();
                }
                else if(type.equals(boolean.class)) {
                    value = console.nextBoolean();
                }  
                else if(type.equals(float.class)) {
                   value = console.nextFloat();
                }
            }
            catch (InputMismatchException e) {    
                if(type.equals(String.class)) 
                    System.out.print("(!) This field need a character string !\n");
                else if(type.equals(int.class))
                    System.out.print("(!) This field need an integer !\n");
                else if(type.equals(boolean.class))
                    System.out.print("(!) This field need a boolean !\n");
                else if(type.equals(float.class))
                    System.out.print("(!)  This field need a decimal number !\n");
                console.nextLine();
            }
        }
        System.out.println("debug : "+value);
        
        return value;         
    }
}
