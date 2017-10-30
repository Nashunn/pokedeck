// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import upmc.pcg.game.Attack;
import upmc.pcg.game.Card;
import upmc.pcg.game.Collection;
import upmc.pcg.game.PokemonCard;

/**
 * CardMenuUI contains statics methods that display cards' menu in the game, to interract with the user
 * @author Nicolas & Gabin
 */
public final class CardMenuUI {
    private static Collection collection = null; 
    private static final Scanner console = new Scanner(System.in);
    
    //Private constructor
    private CardMenuUI() {}
    
    /**
     * Set the collection in this class
     */
    public static void setActualCollection(Collection newCollection) {
        collection = newCollection;
    }
    
    /**
     * Return the card selected by the user in a menu
     */
    public static Card card_consult_menu(Collection collectionGiven, boolean onlyPokemonCard) {
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
            
            if(chosenIndex>=0 && chosenIndex < collection.get_size()) {
                if(!onlyPokemonCard)
                    boolIndexOk = true;
                else {
                    boolIndexOk = check_if_pokemon_card(collectionGiven, chosenIndex);
                        if(!boolIndexOk)
                            System.out.print("(!) Select a pokemon card !\n");
                }
            }
            else
                System.out.println("(!) This card isn't in the collection");
        }
        
        return collectionGiven.get_card(chosenIndex);
    }
    
    /**
     * Check if the card at the index is a pokemon card
     */
    private static boolean check_if_pokemon_card(Collection collectionGiven, int chosenIndex) {
        boolean isPokemonCard = false;
        
        if(collectionGiven.get_card(chosenIndex).get_cardType().equals("pokemon"));
            isPokemonCard = true;
        
        return isPokemonCard;
    }
    
    /**
     * Ask the value for each attributes of the pokemon card
     */
    public static HashMap<String, Object> ask_pokemonCard_attributes() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        valuesForAttributes.put("name", card_ask_name());
        valuesForAttributes.put("specialType", card_ask_energyType());
        valuesForAttributes.put("hp", card_ask_hp());
        valuesForAttributes.put("stage", card_ask_stage());
        if(!valuesForAttributes.get("stage").equals(0))
            valuesForAttributes.put("evolvesFrom", card_ask_evolvesFrom());
        else
            valuesForAttributes.put("evolvesFrom", null);
        valuesForAttributes.put("attacks", card_ask_attacks());        
        
        return valuesForAttributes;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_name() {
        String result = "Default";
        
        do {
            System.out.println(" * Name : ");
            result = console.nextLine();
        }while(result.equals(""));

        return result;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_energyType() {
        String result = "Default";
        
        return result;
    }
    
    /**
     * Explicit
     */
    private static int card_ask_hp() {
        int result = 0;
        
        do {
            try {
                System.out.println(" * Health Point (hp) : ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                console.nextLine();
            }
        } while(result<=0);
        
        return result;
    }
    
    /**
     * Explicit
     */
    private static int card_ask_stage() {
        int result = 0;
        
        do {
            try {
                System.out.println(" * Evolution stage (0 for basic Pokemon) : ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a number !\n");
                console.nextLine();
            }
        } while(result<0);
        
        return result;
    }
    
    /**
     * Explicit, collection need to be set
     */
    private static Card card_ask_evolvesFrom() {
        Card result = null;
        
        collection.list_all_cards();
        if(collection.get_size() < 1)
            result = card_consult_menu(collection, true);
        
        return result;
    }
    
    /**
     * Ask to create attacks for the pokemon
     */
    private static ArrayList<Attack> card_ask_attacks() {
        ArrayList<Attack> result = new ArrayList<>();
        Attack newAttack = null;
        String otherAttack = "n";
        
        System.out.println(" * Attacks :");
        
        do {
            newAttack = new Attack(false); //create an attack without autofill
            result.add(newAttack);
            
            do {
                System.out.println("Do you want to add another attack? (y/n) ");
                otherAttack = console.nextLine();
                console.nextLine();
            }while(!otherAttack.equals("n") && !otherAttack.equals("y"));
            
        }while(otherAttack.equals("y"));
        
        return result;
    }
}
