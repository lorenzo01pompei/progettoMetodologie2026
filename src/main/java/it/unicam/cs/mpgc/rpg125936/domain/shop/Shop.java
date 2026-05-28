package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.repository.GunRepository;
import it.unicam.cs.mpgc.rpg125936.repository.PickaxeRepository;
import it.unicam.cs.mpgc.rpg125936.repository.SpellRepository;

import java.util.*;

public class Shop {

    private final List<WeaponOffer> weapons = new ArrayList<>();
    private final List<ToolOffer> tools = new ArrayList<>();
    private final List<SpellOffer> spells = new ArrayList<>();
    private final Map<String, Integer> materialPrices = new LinkedHashMap<>();

    public Shop() {
        loadWeaponsFromDB();
        loadToolsFromDB();
        loadSpellsFromDB();

        materialPrices.put("Lingotto d'Oro", 10);
        materialPrices.put("Lingotto d'Argento", 5);
        materialPrices.put("Lingotto di Rame", 2);
    }

    private void loadWeaponsFromDB() {
        GunRepository repo = new GunRepository();
        List<Gun> guns = repo.findAll();
        for (Gun g : guns) {
            weapons.add(new WeaponOffer(g.getId(), g.getName(), g.getDamage(), g.getPrice()));
        }
    }

    private void loadSpellsFromDB() {
        SpellRepository repo = new SpellRepository();
        List<Spell> spellsFromDB = repo.findAll();
        for (Spell s : spellsFromDB) {
            spells.add(new SpellOffer(s.getId(), s.getName(), s.getDamage(), s.getPrice()));
        }
    }

    private void loadToolsFromDB() {
        PickaxeRepository repo = new PickaxeRepository();
        List<Pickaxe> pickaxes = repo.findAll();
        for (Pickaxe p : pickaxes) {
            tools.add(new ToolOffer(p.getId(), "Piccone", p.getDurability(), p.getPrice()));
        }
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

    public Map<String, Integer> getMaterialPrices() {
        return materialPrices;
    }

}
