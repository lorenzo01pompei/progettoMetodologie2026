package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.domain.material.MaterialNames;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.repository.ItemRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Inizializza il negozio del gioco caricando armi, strumenti, incantesimi
 * e prezzi dei materiali dai rispettivi repository.
 */
public class ShopInitializer {

    /**
     * Crea e restituisce un negozio completo di tutte le offerte e i listini prezzi.
     *
     * @return negozio inizializzato con armi, strumenti, incantesimi e prezzi materiali
     */
    public Shop create() {
        return new Shop(loadWeapons(), loadTools(), loadSpells(), loadMaterialPrices());
    }

    /// carica dal database le armi del catalogo e le trasforma in WeaponOffer
    private List<WeaponOffer> loadWeapons() {
        List<WeaponOffer> result = new ArrayList<>();
        for (Gun g : new ItemRepository<>(Gun.class).findCatalog()) {
            result.add(new WeaponOffer(g.getId(), g.getName(), g.getDamage(), g.getPrice()));
        }
        return result;
    }

    /// carica dal database gli strumenti del catalogo e li trasforma in ToolOffer
    private List<ToolOffer> loadTools() {
        List<ToolOffer> result = new ArrayList<>();
        for (Pickaxe p : new ItemRepository<>(Pickaxe.class).findCatalog()) {
            result.add(new ToolOffer(p.getId(), p.getName(), p.getDurability(), p.getPrice()));
        }
        return result;
    }

    /// carica dal database gli incantesimi del catalogo e li trasforma in SpellOffer
    private List<SpellOffer> loadSpells() {
        List<SpellOffer> result = new ArrayList<>();
        for (Spell s : new ItemRepository<>(Spell.class).findCatalog()) {
            result.add(new SpellOffer(s.getId(), s.getName(), s.getDamage(), s.getPrice()));
        }
        return result;
    }

    /// costruisce la mappa (nome materiale → prezzo unitario) iterando sull'enum MaterialNames
    private Map<String, Integer> loadMaterialPrices() {
        Map<String, Integer> prices = new LinkedHashMap<>();
        for (MaterialNames m : MaterialNames.values()) {
            prices.put(m.getDisplayName(), m.getValue());
        }
        return prices;
    }
}
