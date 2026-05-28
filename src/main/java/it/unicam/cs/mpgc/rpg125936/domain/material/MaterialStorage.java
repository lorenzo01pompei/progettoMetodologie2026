package it.unicam.cs.mpgc.rpg125936.domain.material;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Embeddable
public class MaterialStorage {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Material.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_player_id")
    private List<Material> materials;

    public MaterialStorage(){
        this.materials = new ArrayList<>();
    }

    public List<List<Material>> getMaterials() {
        Map<String, List<Material>> materialList = new HashMap<>();

        for (Material m : materials) {
            String nomeMateriale = m.getName();

            if (!materialList.containsKey(nomeMateriale)) {
                //se il materiale non è presente in lista, viene creata una lista vuota
                //e viene salvata nella mappa
                materialList.put(nomeMateriale, new ArrayList<>());
            }

            List<Material> singleList = materialList.get(nomeMateriale);

            singleList.add(m);
        }

        return new ArrayList<>(materialList.values());
    }

    public void addMaterial(Material material) {
        this.materials.add(material);
    }

    public int countMaterial(String materialName) {
        int count = 0;
        for (Material m : materials) {
            if (m.getName().equals(materialName)) {
                count++;
            }
        }
        return count;
    }

    public boolean removeMaterials(String materialName, int quantity) {
        if (countMaterial(materialName) < quantity) {
            return false;
        }

        int removed = 0;
        for (int i = materials.size() - 1; i >= 0 && removed < quantity; i--) {
            if (materials.get(i).getName().equals(materialName)) {
                materials.remove(i);
                removed++;
            }
        }
        return true;
    }
}