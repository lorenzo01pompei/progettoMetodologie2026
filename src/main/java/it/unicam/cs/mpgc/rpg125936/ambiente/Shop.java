package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.service.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.service.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.service.shop.WeaponOffer;

import java.util.*;

public class Shop {

    private final List<WeaponOffer> weapons = new ArrayList<>();
    private final List<ToolOffer> tools = new ArrayList<>();
    private final List<SpellOffer> spells = new ArrayList<>();
    private final Map<String, Double> materialPrices = new LinkedHashMap<>();

    public Shop() {
        weapons.add(new WeaponOffer("weapon-1", "Pugnale Arrugginito", 10.0, 5.0));
        weapons.add(new WeaponOffer("weapon-2", "Spada di Ferro", 25.0, 15.0));
        weapons.add(new WeaponOffer("weapon-3", "Ascia da Battaglia", 40.0, 30.0));
        weapons.add(new WeaponOffer("weapon-4", "Spada dell'Eroe", 75.0, 100.0));

        tools.add(new ToolOffer("tool-pickaxe", "Piccone", 500.0, 40.0));

        spells.add(new SpellOffer("spell-1","Veleno", 10.0, 10.0));
        spells.add(new SpellOffer("spell-2","Scarica elettrica", 7.0, 8.0));

        materialPrices.put("Lingotto d'Oro", 10.0);
        materialPrices.put("Lingotto d'Argento", 5.0);
        materialPrices.put("Lingotto di Rame", 2.0);
    }

    public List<WeaponOffer> getWeapons() {
        return weapons;
    }

    public List<ToolOffer> getTools() {
        return tools;
    }

    public List<SpellOffer> getSpells() {
        return spells;
    }

    public Map<String, Double> getMaterialPrices() {
        return materialPrices;
    }

    public WeaponOffer findWeaponById(String id) {
        for (WeaponOffer w : weapons) {
            if (w.getId().equals(id)) return w;
        }
        return null;
    }

    public ToolOffer findToolById(String id) {
        for (ToolOffer t : tools) {
            if (t.getId().equals(id)) return t;
        }
        return null;
    }

    public SpellOffer findSpellById(String id) {
        for (SpellOffer s : spells) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }


}
