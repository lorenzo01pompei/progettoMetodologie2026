package it.unicam.cs.mpgc.rpg125936.service.mine;

import it.unicam.cs.mpgc.rpg125936.domain.material.Material;

/// classe usata per rappresentare l'esito di un singolo tentativo di scavata
public class MiningResultDTO {
    private final boolean success;
    private final String message;
    private final Material material;
    private final int remainingDurability;

    public MiningResultDTO(boolean success, String message, Material material, int remainingDurability) {
        this.success = success;
        this.message = message;
        this.material = material;
        this.remainingDurability = remainingDurability;
    }

    public String getMessage() { return message; }
    public Material getMaterial() { return material; }
}
