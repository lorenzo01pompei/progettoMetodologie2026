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

/**
 * Inizializza il negozio del gioco caricando armi, strumenti, incantesimi
 * e prezzi dei materiali dai rispettivi repository.
 */
public class ShopInitializer {

    /// prezzo di vendita per unità di oro
    private static final int GOLD_PRICE = 10;
    /// prezzo di vendita per unità di argento
    private static final int SILVER_PRICE = 5;
    /// prezzo di vendita per unità di rame
    private static final int COPPER_PRICE = 2;

    /**
     * Crea e restituisce un negozio completo di tutte le offerte e i listini prezzi.
     *
     * @return negozio inizializzato con armi, strumenti, incantesimi e prezzi materiali
     */
    public Shop create() {
        return new Shop(loadWeapons(), loadTools(), loadSpells(), loadMaterialPrices());
    }

    /// carica dal database tutte le armi e le trasforma in WeaponOffer
    private List<WeaponOffer> loadWeapons() {
        GunRepository repo = new GunRepository();
        List<WeaponOffer> result = new ArrayList<>();
        for (Gun g : repo.findAll()) {
            result.add(new WeaponOffer(g.getId(), g.getName(), g.getDamage(), g.getPrice()));
        }
        return result;
    }

    /// carica dal database tutti gli strumenti e li trasforma in ToolOffer
    private List<ToolOffer> loadTools() {
        PickaxeRepository repo = new PickaxeRepository();
        List<ToolOffer> result = new ArrayList<>();
        for (Pickaxe p : repo.findAll()) {
            result.add(new ToolOffer(p.getId(), p.getName(), p.getDurability(), p.getPrice()));
        }
        return result;
    }

    /// carica dal database tutti gli incantesimi e li trasforma in SpellOffer
    private List<SpellOffer> loadSpells() {
        SpellRepository repo = new SpellRepository();
        List<SpellOffer> result = new ArrayList<>();
        for (Spell s : repo.findAll()) {
            result.add(new SpellOffer(s.getId(), s.getName(), s.getDamage(), s.getPrice()));
        }
        return result;
    }

    /// costruisce la mappa (nome materiale → prezzo unitario) per la vendita dei materiali
    private Map<String, Integer> loadMaterialPrices() {
        Map<String, Integer> prices = new LinkedHashMap<>();
        prices.put(MaterialNames.GOLD, GOLD_PRICE);
        prices.put(MaterialNames.SILVER, SILVER_PRICE);
        prices.put(MaterialNames.COPPER, COPPER_PRICE);
        return prices;
    }
}
