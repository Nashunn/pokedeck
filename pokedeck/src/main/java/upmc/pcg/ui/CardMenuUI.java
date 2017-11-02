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
import upmc.pcg.game.EnergyCard;
import upmc.pcg.game.PokemonCard;
import upmc.pcg.game.TrainerCard;

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
        
        while(!boolIndexOk && collectionGiven.get_size()>=1) {
            try {
                System.out.println("Select a card : ");
                chosenIndex = console.nextInt()-1;
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a card number !\n");
                GameUI.clear_console_buffer(console);
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
        
        if(collectionGiven.get_size()<1)
            return null;
        else
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
        
        GameUI.clear_console_buffer(console);
        
        valuesForAttributes.put("name", card_ask_name());
        valuesForAttributes.put("specialType", card_ask_energyType());
        valuesForAttributes.put("hp", card_ask_hp());
        valuesForAttributes.put("stage", card_ask_stage());
        if(!valuesForAttributes.get("stage").equals(0))
            valuesForAttributes.put("evolvesFrom", card_ask_evolvesFrom());
        else
            valuesForAttributes.put("evolvesFrom", null);
        valuesForAttributes.put("attacks", card_ask_attacks());
        valuesForAttributes.put("weaknessType", card_ask_weaknessType());
        valuesForAttributes.put("resistanceType", card_ask_resistanceType());
        valuesForAttributes.put("cardNb", card_ask_cardNb((String)valuesForAttributes.get("name")));
        
        return valuesForAttributes;
    }
    
    /**
     * Ask the value for each attributes of the pokemon card
     */
    public static HashMap<String, Object> ask_trainerCard_attributes() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        GameUI.clear_console_buffer(console);
        
        valuesForAttributes.put("name", card_ask_name());
        valuesForAttributes.put("specialType", card_ask_trainerType());
        valuesForAttributes.put("explanation", card_ask_explanation());
        valuesForAttributes.put("trainerRule", card_ask_trainerRule());
        valuesForAttributes.put("cardNb", card_ask_cardNb((String)valuesForAttributes.get("name")));
        
        return valuesForAttributes;
    }
    
    /**
     * Ask the value for each attributes of the energy card
     */
    public static HashMap<String, Object> ask_energyCard_attributes() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        int cardNb = 0;
        
        GameUI.clear_console_buffer(console);
        
        valuesForAttributes.put("specialType", card_ask_energyType());
        valuesForAttributes.put("name", "[Energy Card] "+(String)valuesForAttributes.get("specialType"));
        cardNb = find_energy_cardNb((String)valuesForAttributes.get("specialType"))+1;
        valuesForAttributes.put("cardNb", -cardNb);//negative card number for energy
        
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
        
        System.out.println(" * Energy type : ");
        MenuUI.print_energies();
        result = EnergyCard.ENERGY_TYPES[MenuUI.ask_energy()];
        
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
                GameUI.clear_console_buffer(console);
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
                GameUI.clear_console_buffer(console);
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
                GameUI.clear_console_buffer(console);
                System.out.println("Do you want to add another attack? (y/n) ");
                otherAttack = console.nextLine();
            }while(!otherAttack.equals("n") && !otherAttack.equals("y"));
            
        }while(otherAttack.equals("y"));
        
        return result;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_weaknessType() {
        String result = "Default";
        
        System.out.println(" * Weakness type : ");
        MenuUI.print_energies();
        result = EnergyCard.ENERGY_TYPES[MenuUI.ask_energy()];
        
        return result;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_resistanceType() {
        String result = "Default";
        
        System.out.println(" * Resistance type : ");
        MenuUI.print_energies();
        result = EnergyCard.ENERGY_TYPES[MenuUI.ask_energy()];
        
        return result;
    }
    
    /**
     * Explicit, verify if the cardNb is available in the collection
     */
    private static int card_ask_cardNb(String actualCardName) {
        int result = -1;
        
        do {
            try {
                System.out.println(" * collector card number : ");
                result = console.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("(!) Select a positive number !\n");
                GameUI.clear_console_buffer(console);
            }
        } while(result<=0 && !collection.cardNb_available(actualCardName, result));
        
        return result;
    }
    
    /**
     * Explicit, for trainer card
     */
    private static String card_ask_trainerType() {
        String result = "Default";
        
        System.out.println(" * Trainer type : ");
        MenuUI.print_trainerType();
        result = TrainerCard.TRAINER_TYPES[MenuUI.ask_trainerType()];
        
        return result;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_explanation() {
        String result = "No explanation";
        
        do {
            System.out.println(" * Explanation : ");
            result = console.nextLine();
        }while(result.equals(""));

        return result;
    }
    
    /**
     * Explicit
     */
    private static String card_ask_trainerRule() {
        String result = "No rule";
        
        do {
            System.out.println(" * Trainer Rule : ");
            result = console.nextLine();
        }while(result.equals(""));

        return result;
    }
    
    /**
     * Find the cardNb for an energyCard, with its energyType
     */
    private static int find_energy_cardNb(String energyType) {
        int result = -1;
        int energyIndex = 0;
        boolean cardNbFound = false;

        while(!cardNbFound) {
            if(energyType.equals(EnergyCard.ENERGY_TYPES[energyIndex])) {
                cardNbFound = true;
                result = energyIndex;
            }
            else
                energyIndex++;
        }
        
        return result;
    }
}
