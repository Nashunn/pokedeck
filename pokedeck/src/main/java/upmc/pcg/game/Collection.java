// School project : Pokedeck
//
// Write by Nicolas BOULLET and Gabin FIQUET
// Studying at UPMC in Licence Pro Projet Web (2017/2018)

package upmc.pcg.game;

import java.util.ArrayList;
import java.util.Iterator;
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
    
    /**
     * Display all cards in the collection in the form of a list
     */
    public void list_all_cards(){
        Iterator<Card> cardIterator = cards.iterator();
        
        for(int cardIndex = 0; cardIterator.hasNext(); cardIndex++) {
            Card card = cardIterator.next();
            System.out.println((cardIndex+1)+". "+card.get_name());
        }
    }
    
    /**
     * Add a card in the collection
     */
    public void add_card(Card newCard){
        cards.add(newCard);
    }
       
    /**
     * Return the card saved at index position in the list
     */
    public Card get_card(int index) {
        return cards.get(index);
    }
    
    /**
     * Return the size of the collection
     */
    public int get_size() {
        return cards.size();
    }
}
