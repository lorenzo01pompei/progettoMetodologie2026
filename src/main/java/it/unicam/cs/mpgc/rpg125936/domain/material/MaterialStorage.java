package it.unicam.cs.mpgc.rpg125936.domain.material;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Embeddable
public class MaterialStorage {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Material.class)
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
        // Controlliamo prima se ne abbiamo abbastanza
        if (countMaterial(materialName) < quantity) {
            return false;
        }

        int removed = 0;
        // Usiamo un Iterator per rimuovere gli oggetti in sicurezza dalla lista
        Iterator<Material> iterator = materials.iterator();

        while (iterator.hasNext() && removed < quantity) {
            Material m = iterator.next();
            if (m.getName().equals(materialName)) {
                iterator.remove();
                removed++;
            }
        }
        return true;
    }
}