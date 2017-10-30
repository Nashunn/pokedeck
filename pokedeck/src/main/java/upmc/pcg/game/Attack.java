// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.util.ArrayList;
import java.util.HashMap;
import upmc.pcg.ui.MenuUI;

/**
 * EnergyCard is a representation of pokemon energy card
 * @author Nicolas & Gabin
 */
public class Attack {
    private String name;
    private ArrayList<String> neededEnergy = new ArrayList<String>();
    private int damage;
    private String description;
    
    /**
     * Default constructor for a basic attack if autoFill is true, ask the user to fill the fields if false
     */    
    public Attack(boolean autoFill) {
        if(autoFill) {
            this.name = "Basic attack";
            this.neededEnergy.add(EnergyCard.ENERGY_TYPES[EnergyCard.ENERGY_TYPES.length-1]);
            this.damage = 10;
            this.description = "The most basic attack a pokemon can do. Seriously, it's just a slap.";
        }
        else {
            fill_attack();
        }
    }
    
    /**
     * Ask the user how to fill the attacks
     */
    private void fill_attack() {
        HashMap<String, Object> valuesForAttributes = new HashMap<>();
        
        valuesForAttributes = MenuUI.attack_ask_all();
        
        this.name = (String)valuesForAttributes.get("name");
        this.neededEnergy = (ArrayList<String>)valuesForAttributes.get("neededEnergy");
        this.damage = (int)valuesForAttributes.get("damage");
        this.description = (String)valuesForAttributes.get("description");
        
        System.out.println("debug : "+this.name);
    }
}
