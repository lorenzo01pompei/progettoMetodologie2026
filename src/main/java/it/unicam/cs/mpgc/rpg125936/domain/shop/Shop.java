package it.unicam.cs.mpgc.rpg125936.domain.shop;

import java.util.List;
import java.util.Map;

/**
 * Negozio del gioco. Contiene le offerte di armi, strumenti e incantesimi
 * che si possono comprare
 */
public class Shop {

    private final List<WeaponOffer> weapons;
    private final List<ToolOffer> tools;
    private final List<SpellOffer> spells;
    private final Map<String, Integer> materialPrices;

    public Shop(List<WeaponOffer> weapons, List<ToolOffer> tools, List<SpellOffer> spells, Map<String, Integer> materialPrices) {
        this.weapons = weapons;
        this.tools = tools;
        this.spells = spells;
        this.materialPrices = materialPrices;
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
