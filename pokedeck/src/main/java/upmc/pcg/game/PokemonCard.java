// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import upmc.pcg.ui.MenuUI;

/**
 * PokemonCard is a representation of pokemon card
 * @author Nicolas & Gabin
 */
public class PokemonCard extends Card {
    private int hp;
    private int stage;
    private ArrayList<Attack> attacks = new ArrayList<Attack>();
    private PokemonCard evolvesFrom = null;
    private String weaknessType;
    private String resistanceType;
    private int cardNb;
    
    public PokemonCard() {};

    /**
     * Create a pokemon card with every attributes
     */
    @Override
    public void create() {
        HashMap<String, Class> cardAttributes = new HashMap<>();
        ArrayList attributesValues = new ArrayList();
        
        MenuUI.print_create_card_msg("pokemon");
        cardAttributes = get_card_attributes();
        attributesValues = MenuUI.ask_card_attributes_values(cardAttributes);
    }
    
    /**
     * Get every attributes of the card with their type, except when they are static   
     */
    private static HashMap<String, Class> get_card_attributes() {
        Field parentFieldsArray[] = Card.class.getDeclaredFields();
        Field childFieldsArray[] = PokemonCard.class.getDeclaredFields();
        HashMap<String, Class> resultFieldsArray = new HashMap<>();
        
        resultFieldsArray.putAll(get_non_static_attributes_from(parentFieldsArray));
        resultFieldsArray.putAll(get_non_static_attributes_from(childFieldsArray));
        
        return resultFieldsArray;
    }
    
    /**
     * Explicit 
     */
    private static HashMap<String, Class> get_non_static_attributes_from(Field fieldsArray[]) {
        HashMap<String, Class> resultFieldsArray = new HashMap<>();
        
        for(Field field : fieldsArray) {
            if (!Modifier.isStatic(field.getModifiers()))
                resultFieldsArray.put(field.getName(), field.getType());
        }
        
        return resultFieldsArray;
    }
}
