package it.unicam.cs.mpgc.rpg125936.domain.material;

import java.util.ArrayList;
import java.util.List;

public class MaterialStorage {
    private List<List<Material>> materials;

    public MaterialStorage(){
        this.materials = new ArrayList<>();
    }

    public List<List<Material>> getMaterials() {
        return materials;
    }

    public void addMaterial(Material material) {
        for (List<Material> list : materials) {
            if (!list.isEmpty() && list.get(0).getName().equals(material.getName())) {
                list.add(material);
                return;
            }
        }
        List<Material> newList = new ArrayList<>();
        newList.add(material);
        this.materials.add(newList);
    }
}
