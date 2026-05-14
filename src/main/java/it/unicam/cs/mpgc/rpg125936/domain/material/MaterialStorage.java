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

    public int countMaterial(String materialName) {
        for (List<Material> list : materials) {
            if (!list.isEmpty() && list.get(0).getName().equals(materialName)) {
                return list.size();
            }
        }
        return 0;
    }

    public boolean removeMaterials(String materialName, int quantity) {
        for (List<Material> list : materials) {
            if (!list.isEmpty() && list.get(0).getName().equals(materialName)) {
                if (list.size() < quantity) return false;
                for (int i = 0; i < quantity; i++) {
                    list.remove(0);
                }
                return true;
            }
        }
        return false;
    }
}
