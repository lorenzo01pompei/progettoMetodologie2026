package it.unicam.cs.mpgc.rpg125936.domain.material;

/**
 * Enumerazione dei materiali estraibili nel gioco.
 * Ogni costante indica per il materiale il suo valore
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

    /// @return il nome swl materiale (lingotto di ...)
    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }
}
