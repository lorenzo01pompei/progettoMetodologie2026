package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;

/**
 * Rappresenta un'incantesimo presente nel catalogo del negozio.
 * Contiene le informazioni di vendita (id, nome, prezzo)
 * inoltre consente di istanziare l'item al momento dell'acquisto.
 */

public class SpellOffer {
    private final String id;
    private final String name;
    private final double damage;
    private final double price;

    /**
     * Crea una nuova offerta per uno strumento.
     *
     * @param id      identificativo univoco dell'offerta nel catalogo
     * @param name    nome dello strumento
     * @param price   prezzo per l'acquisto
     */
    public SpellOffer(String id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    /** @return identificativo univoco dell'offerta */
    public String getId() { return id; }

    /** @return nome visualizzato dell'incantesimo */
    public String getName() { return name; }


    /** @return prezzo per l'acquisto */
    public double getPrice() { return price; }

    /** @return danno dell'incantesimo */
    public double getDamage() { return damage; }

    /**
     * Crea una nuova istanza di @Spell
     * Metodo usato da ShopService al momento dell'acquisto
     *
     * @return una nuova istanza di Spell configurata
     */

    public Spell createSpell() {
        return new Spell(name, damage);
    }
}
