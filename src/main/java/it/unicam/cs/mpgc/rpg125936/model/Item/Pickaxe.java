package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.ambiente.GameLocation;
import it.unicam.cs.mpgc.rpg125936.ambiente.Miniera;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

/**
 * Rappresenta un piccone (strumento) che il giocatore può utilizzare all'interno del gioco.
 * Implementa l'interfaccia ToolItem, il che gli permette di interagire in modo specifico
 * con determinati ambienti (come la Miniera).
 */
public class Pickaxe implements ToolItem {

    private double durability;

    /**
     * Costruttore di default. Imposta la durabilità iniziale a 500.
     */
    public Pickaxe(){
        this.durability = 500;
    }

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }

    /**
     * Decrementa la durabilità del piccone di 1 unità, a patto che non sia già a 0.
     */
    public void decreaseDurability(){
        if(durability!=0){
            this.durability-=1;
        }
    }

    /**
     * Metodo per l'uso dell'oggetto nell'ambiente di gioco.
     * Riceve la posizione corrente e determina se l'oggetto può essere utilizzato.
     *
     * PARAMETRO location:
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
        // Il piccone funziona solo se ci troviamo in una Miniera
        if (location instanceof Miniera) {
            if (this.durability > 0) {
                this.decreaseDurability();
                return true;
            }
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
