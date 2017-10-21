// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

/**
 * Card is a general representation for all type of pokemon cards
 * @author Nicolas & Gabin
 */
public class Card {
    protected String name = "Default";
    protected String cardType;
    protected String specialType; //Pokemon type, Trainer type or Energy type
    public static String CARD_TYPES[] = {"pokemon", "trainer", "energy"};
    
    /**
     * Constructor which take the name of the card, type and specialType
     */
    public Card(String name, String cardType, String specialType) {
        this.name = name;
        this.cardType = cardType;
        this.specialType = specialType;
    }
}
