package it.unicam.cs.mpgc.rpg125936.service.mine;

import it.unicam.cs.mpgc.rpg125936.domain.material.Material;

public class MiningResultDTO {
    private final boolean success;
    private final String message;
    private final Material material;
    private final double remainingDurability;

    public MiningResultDTO(boolean success, String message, Material material, double remainingDurability) {
        this.success = success;
        this.message = message;
        this.material = material;
        this.remainingDurability = remainingDurability;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Material getMaterial() { return material; }
    public double getRemainingDurability() { return remainingDurability; }
}
