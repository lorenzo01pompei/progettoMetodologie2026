package it.unicam.cs.mpgc.rpg125936.domain.material;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contenitore per i materiali posseduti da un utente.
 * Memorizza una lista di Material.
 */
@Embeddable
public class MaterialStorage {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Material.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_player_id")
    private List<Material> materials;

    public MaterialStorage(){
        this.materials = new ArrayList<>();
    }

    /// restituisce una Map<String, List<Material>> raggruppata per nome del materiale
    public Map<String, List<Material>> getMaterials() {
        Map<String, List<Material>> materialList = new HashMap<>();

        for (Material m : materials) {
            String materialName = m.getName();
            materialList.computeIfAbsent(materialName, k -> new ArrayList<>()).add(m);
        }

        return materialList;
    }

    public void addMaterial(Material material) {
        this.materials.add(material);
    }

    public void removeAll() {
        materials.clear();
    }
}