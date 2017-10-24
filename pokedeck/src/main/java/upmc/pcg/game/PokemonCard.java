// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.util.ArrayList;

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

    @Override
    public void create() {
        System.out.println("TODO : creation of pokemon card");
    }
    
    
}
