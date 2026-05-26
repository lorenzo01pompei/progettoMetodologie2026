package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.location.GameLocation;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Rappresenta un piccone (strumento) che il giocatore può utilizzare all'interno del gioco.
 * Implementa l'interfaccia ToolItem, il che gli permette di interagire in modo specifico
 * con determinati ambienti (come la Miniera).
 */

@Entity
@DiscriminatorValue("PICKAXE")
public class Pickaxe extends AbstractItem implements ToolItem {

    @Column(name= "durability")
    private double durability;
    @Column(name= "price")
    private double price;

    /**
     * Costruttore di default. Imposta la durabilità iniziale a 500.
     */
    public Pickaxe(){
        this.durability = 500;
        this.price =40;
    }

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**Decrementa la durabilità del piccone di 1 unità, a patto che non sia già a 0.
     */
    public void decreaseDurability(){
        if(durability!=0){
            this.durability-=1;
        }
    }

    /**metodo che riceve la posizione corrente e determina se l'oggetto può essere utilizzato.
     *
     * Passando l'istanza di GameLocation (l'ambiente in cui ci si trova), l'oggetto "Piccone"
     * il piccone verifica di trovarsi effettivamente in una Miniera. Se l'utente provasse ad usare
     * il piccone nella Lobby o in una ipotetica Foresta, l'interazione fallirebbe, restituendo false.
     *
     * @param location Il luogo corrente in cui si sta provando a usare l'oggetto.
     * @return true se l'interazione è andata a buon fine (ci si trova in miniera e il piccone ha durabilità),
     *         false in caso contrario.
     */
    @Override
    public boolean interact(GameLocation location){
        if (location.canBeMined() && this.durability > 0) {
            this.decreaseDurability();
            return true;
        }
        return false;
    }

    /**
     * Crea un clone del piccone con la stessa durabilità attuale.
     * Utilizzato per isolare gli item durante i combattimenti o altre logiche di clonazione.
     * @return un nuovo oggetto Pickaxe clonato.
     */
    @Override
    public Item copy() {
        Pickaxe p = new Pickaxe();
        p.setDurability(this.durability);
        return p;
    }

}
