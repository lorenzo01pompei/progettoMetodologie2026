package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.domain.material.MaterialNames;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.repository.GunRepository;
import it.unicam.cs.mpgc.rpg125936.repository.PickaxeRepository;
import it.unicam.cs.mpgc.rpg125936.repository.SpellRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShopInitializer {

    public Shop create() {
        return new Shop(loadWeapons(), loadTools(), loadSpells(), loadMaterialPrices());
    }

    private List<WeaponOffer> loadWeapons() {
        GunRepository repo = new GunRepository();
        List<WeaponOffer> result = new ArrayList<>();
        for (Gun g : repo.findAll()) {
            result.add(new WeaponOffer(g.getId(), g.getName(), g.getDamage(), g.getPrice()));
        }
        return result;
    }

    private List<ToolOffer> loadTools() {
        PickaxeRepository repo = new PickaxeRepository();
        List<ToolOffer> result = new ArrayList<>();
        for (Pickaxe p : repo.findAll()) {
            result.add(new ToolOffer(p.getId(), p.getName(), p.getDurability(), p.getPrice()));
        }
        return result;
    }

    private List<SpellOffer> loadSpells() {
        SpellRepository repo = new SpellRepository();
        List<SpellOffer> result = new ArrayList<>();
        for (Spell s : repo.findAll()) {
            result.add(new SpellOffer(s.getId(), s.getName(), s.getDamage(), s.getPrice()));
        }
        return result;
    }

    private Map<String, Integer> loadMaterialPrices() {
        Map<String, Integer> prices = new LinkedHashMap<>();
        prices.put(MaterialNames.GOLD, 10);
        prices.put(MaterialNames.SILVER, 5);
        prices.put(MaterialNames.COPPER, 2);
        return prices;
    }
}
