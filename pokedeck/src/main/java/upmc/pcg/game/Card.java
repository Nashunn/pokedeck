// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

/**
 * Card is an abstract representation of all types of pokemon cards
 * @author Nicolas & Gabin
 */
public abstract class Card {
    protected String name = "Default";
    protected String cardType;
    protected String specialType; //Pokemon type, Trainer type or Energy type
    public static String CARD_TYPES[] = {"pokemon", "trainer", "energy"};
    
    /**
     * Return the name of the card in the form of a string
     */
    public String get_name() {
        return this.name;
    }
}
