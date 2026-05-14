package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;

/**
 * Rappresenta un'arma presente nel catalogo del negozio.
 * Contiene le informazioni di vendita (id, nome, danno, prezzo)
 * inoltre consente di istanziare l'item al momento dell'acquisto.
 */
public class WeaponOffer {
    private final String id;
    private final String name;
    private final double damage;
    private final double price;

    /**
     * Crea una nuova offerta per un'arma.
     *
     * @param id     identificativo univoco dell'offerta nel catalogo
     * @param name   nome dell'arma
     * @param damage danno dell'arma
     * @param price  prezzo per l'acquisto
     */
    public WeaponOffer(String id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    /** @return identificativo univoco dell'offerta */
    public String getId() { return id; }

    /** @return nome dell'arma */
    public String getName() { return name; }

    /** @return danno dell'arma */
    public double getDamage() { return damage; }

    /** @return prezzo per l'acquisto*/
    public double getPrice() { return price; }

    /**
     * Crea una nuova istanza di @Gun corrispondente a questa offerta.
     * Metodo usato da ShopService al momento dell'acquisto
     *
     * @return una nuova istanza di Gun con i parametri dell'offerta
     */
    public Gun createItem() {
        return new Gun(name, damage, price);
    }
}
