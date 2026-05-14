package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;

/**
 * Rappresenta uno strumento presente nel catalogo del negozio.
 * Contiene le informazioni di vendita (id, nome, durabilità garantita, prezzo)
 * inoltre consente di istanziare l'item al momento dell'acquisto.
 */

public class ToolOffer {
    private final String id;
    private final String name;
    private final double maxUses;
    private final double price;

    /**
     * Crea una nuova offerta per uno strumento.
     *
     * @param id      identificativo univoco dell'offerta nel catalogo
     * @param name    nome dello strumento
     * @param maxUses durabilità massima (numero di utilizzi garantiti)
     * @param price   prezzo per l'acquisto
     */
    public ToolOffer(String id, String name, double maxUses, double price) {
        this.id = id;
        this.name = name;
        this.maxUses = maxUses;
        this.price = price;
    }

    /** @return identificativo univoco dell'offerta */
    public String getId() { return id; }

    /** @return nome visualizzato dello strumento */
    public String getName() { return name; }

    /** @return numero di utilizzi */
    public double getMaxUses() { return maxUses; }

    /** @return prezzo per l'acquisto */
    public double getPrice() { return price; }

    /**
     * Crea una nuova istanza di @Pickaxe con la durabilità specificata
     * Metodo usato da ShopService al momento dell'acquisto
     *
     * @return una nuova istanza di Pickaxe configurata
     */
    public Pickaxe createItem() {
        Pickaxe p = new Pickaxe();
        p.setDurability(maxUses);
        return p;
    }
}
