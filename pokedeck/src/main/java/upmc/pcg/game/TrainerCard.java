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
    private String trainer;
    public static String TRAINER_TYPES[] = {"item", "supporter", "stadium"}; 

    @Override
    public void create() {
        System.out.println("TODO : creation of trainer card");
    }
}
