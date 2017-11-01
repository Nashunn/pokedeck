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
import upmc.pcg.ui.CardMenuUI;
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
    
    public PokemonCard() {};

    /**
     * Create a pokemon card with every attributes
     */
    @Override
    public void create() {
        MenuUI.print_create_card_msg("pokemon");
        fill_card();
    }
    
    /**
     * Fill the card with 
     */
    private void fill_card() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        valuesForAttributes = CardMenuUI.ask_pokemonCard_attributes();
        
        this.cardType = CARD_TYPES[0]; //pokemon
        this.name = (String)valuesForAttributes.get("name");
        this.specialType = (String)valuesForAttributes.get("specialType");
        this.hp = (int)valuesForAttributes.get("hp");
        this.stage = (int)valuesForAttributes.get("stage");
        this.evolvesFrom = (PokemonCard)valuesForAttributes.get("evolvesFrom");
        this.attacks = (ArrayList<Attack>)valuesForAttributes.get("attacks");
        this.weaknessType = (String)valuesForAttributes.get("weaknessType");
        this.resistanceType = (String)valuesForAttributes.get("resistanceType");
        this.cardNb = (int)valuesForAttributes.get("cardNb");
    }
}
