// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

/**
 * TrainerCard is a representation of pokemon trainer card
 * @author Nicolas & Gabin
 */
public class TrainerCard extends Card {
    private String explanation;
    private String trainerRule;
    public static String TRAINER_TYPES[] = {"item", "supporter", "stadium"}; 

    @Override
    public void create() {
        System.out.println("TODO : creation of trainer card");
    }
    
    /**
     * Display all the informations of the card for the user and return them in the form of a string
     */
    @Override
    public String toString() {
        String cardInformation = "";
        
        cardInformation += "---------------------------\n Trainer Card Information :\n\n";
        cardInformation += "| Name : "+this.name+"\n";
        cardInformation += "| Trainer type : "+this.specialType+"\n";
        cardInformation += "| Explanation : \n|    "+this.explanation+"\n";
        cardInformation += "| Trainer rule : "+this.trainerRule+"\n";
        cardInformation += "| Collection card number : "+this.cardNb+"\n";
        cardInformation += "---------------------------\n";
        
        return cardInformation;
    }
}
