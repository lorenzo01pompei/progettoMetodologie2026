package it.unicam.cs.mpgc.rpg125936.domain.item;

/**
 * Interfaccia base di tutti gli item del gioco.
 * Il metodo copy permette di creare una copia indipendente
 * dell'item, necessaria per isolare gli item durante combatttimento e acquisti.
 * In questo modo qualsiasi cosa succeda agli item durante il gioco non va a intaccare
 * l'oggetto vero e proprio caricato sul database (che poi finisce
 * nello shop riacquistabile in formato normale)
 */
public interface Item {
    Item copy();
    String getName();
    double getPrice();
}
