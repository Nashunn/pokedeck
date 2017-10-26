// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.ui;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import upmc.pcg.game.Card;
import upmc.pcg.game.Collection;

/**
 * CardMenuUI contains statics methods that display cards' menu in the game, to interract with the user
 * @author Nicolas & Gabin
 */
public final class CardMenuUI {
    private static final Scanner console = new Scanner(System.in);
    
    //Private constructor
    private CardMenuUI() {}
    
    /**
     * Return the card selected by the user in a menu
     */
    public static Card card_consult_menu(Collection collection) {
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
     * Ask the value for each attributes of the pokemon card
     */
    public static HashMap<String, Object> ask_pokemonCard_attributes() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        valuesForAttributes.put("name", card_ask_name());
        
        return valuesForAttributes;
    }
    
    /**
     * Ask the name for card, independently of the card type
     */
    public static String card_ask_name() {
        String result = "Default";
        
        return result;
    }
}
