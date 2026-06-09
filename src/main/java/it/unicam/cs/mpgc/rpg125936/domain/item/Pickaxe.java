package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.location.GameLocation;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Strumento che permette di interagire con le Miniere.
 * Ogni uso consuma un punto di durabilita; quando la durabilita arriva a zero
 * il piccone non puo piu essere utilizzato.
 */
@Entity
@DiscriminatorValue("PICKAXE")
public class Pickaxe extends AbstractItem implements ToolItem {

    @Column(name= "durability")
    private int durability;

    public static final String DEFAULT_NAME = "Piccone";
    public static final int DEFAULT_DURABILITY = 500;
    public static final double DEFAULT_PRICE = 40;

    public Pickaxe(String name, int durability, double price) {
        super(name, price);
        this.durability = durability;
    }

    public Pickaxe() {
        this(DEFAULT_NAME, DEFAULT_DURABILITY, DEFAULT_PRICE);
    }

    public int getDurability() {
        return durability;
    }


    public void decreaseDurability(){
        if(durability!=0){
            this.durability-=1;
        }
    }

    /**
     * se questo metodo viene applicato ad una location che può essere minata
     * e viene invocato su un oggetto con durabilità > 0
     * viene diminuita la durabilità del piccone
     * @param location è il luogo in cui viene usato il piccone
     * @return
     */
    @Override
    public boolean interact(GameLocation location){
        if (location.canBeMined() && this.durability > 0) {
            this.decreaseDurability();
            return true;
        }
        return false;
    }

    @Override
    public Item copy() {
        return new Pickaxe(getName(), this.durability, getPrice());
    }

}
