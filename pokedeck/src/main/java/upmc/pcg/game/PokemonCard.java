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
    private int cardNb;
    
    public PokemonCard() {};

    /**
     * Create a pokemon card with every attributes
     */
    @Override
    public void create() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        MenuUI.print_create_card_msg("pokemon");
        valuesForAttributes = CardMenuUI.ask_pokemonCard_attributes();
    }
}
