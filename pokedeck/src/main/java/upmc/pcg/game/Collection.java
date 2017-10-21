// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection represents a pokemon cards collection
 * @author Nicolas & Gabin
 */
public class Collection {
    private String owner; 
    private List<Card> cards = new ArrayList<Card>();
    
    /**
     * Constructor that create a collection for the player with his name
     */
    public Collection(String name) {
        this.owner = name;
    }
}
