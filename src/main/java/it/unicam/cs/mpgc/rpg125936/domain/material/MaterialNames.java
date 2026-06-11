package it.unicam.cs.mpgc.rpg125936.domain.material;

/**
 * Enum dei materiali estraibili nel gioco.
 * Ogni costante accoppia il nome visualizzato al giocatore con il valore
 * base in monete, eliminando la duplicazione tra MinieraService e ShopInitializer.
 */
public enum MaterialNames {

    GOLD("Lingotto d'Oro", 10),
    SILVER("Lingotto d'Argento", 5),
    COPPER("Lingotto di Rame", 2);

    private final String displayName;
    private final int value;

    MaterialNames(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    /** @return il nome visualizzato al giocatore (es. "Lingotto d'Oro") */
    public String getDisplayName() {
        return displayName;
    }

    /** @return il valore base in monete del materiale */
    public int getValue() {
        return value;
    }
}
