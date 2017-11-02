// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

/**
 * EnergyCard is a representation of pokemon energy card
 * @author Nicolas & Gabin
 */
public class EnergyCard extends Card {
    public static String ENERGY_TYPES[] = {
        "Grass",
        "Fire",
        "Water",
        "Lightning",
        "Psychic",
        "Fighting",
        "Darkness",
        "Metal",
        "Fairy",
        "Dragon",
        "Colorless"
    };

    @Override
    public void create() {
        System.out.println("TODO : creation of energy card");
    }
    
    /**
     * Display all the informations of the card for the user and return them in the form of a string
     */
    @Override
    public String toString() {
        String cardInformation = "";
        
        cardInformation += "---------------------------\n Energy Card Information :\n\n";
        cardInformation += "| Name : "+this.name+"\n";
        cardInformation += "| Energy type : "+this.specialType+"\n";
        cardInformation += "| Collection card number : "+this.cardNb+"\n";
        cardInformation += "---------------------------\n";
        
        return cardInformation;
    }
}
