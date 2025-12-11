package src.Market;

import src.Item.Armor;
import src.Item.Item;
import src.Item.Potion;
import src.Item.Spell;
import src.Item.Weapon;
import src.Utils.Default.DefaultReader;

import java.util.*;


// MarketItemTemplates loads all item data from new_defaults directory
public class MarketItemTemplates {

    // Get a random weapon from Weaponry.txt
    private static Weapon getRandomWeapon(Random random) {
        List<DefaultReader.WeaponTemplate> templates = DefaultReader.loadWeapons();
        if (templates.isEmpty()) {
            // Fallback
            return new Weapon("Rusty Sword", 50, 1, 10, 1);
        }
        DefaultReader.WeaponTemplate wt = templates.get(random.nextInt(templates.size()));
        return new Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands);
    }

    // Get a random armor from Armory.txt
    private static Armor getRandomArmor(Random random) {
        List<DefaultReader.ArmorTemplate> templates = DefaultReader.loadArmor();
        if (templates.isEmpty()) {
            // Fallback
            return new Armor("Torn Cloth", 20, 1, 2);
        }
        DefaultReader.ArmorTemplate at = templates.get(random.nextInt(templates.size()));
        return new Armor(at.name, at.cost, at.level, at.reduction);
    }

    // Get a random potion from Potions.txt
    private static Potion getRandomPotion(Random random) {
        List<DefaultReader.PotionTemplate> templates = DefaultReader.loadPotions();
        if (templates.isEmpty()) {
            // Fallback
            return new Potion("Tiny HP Potion", 20, 1, 20, "HP");
        }
        DefaultReader.PotionTemplate pt = templates.get(random.nextInt(templates.size()));
        return new Potion(pt.name, pt.cost, pt.level, pt.increase, pt.affected);
    }

    // Get a random spell from FireSpells/IceSpells/LightningSpells.txt
    private static Spell getRandomSpell(Random random) {
        List<DefaultReader.SpellTemplate> templates = DefaultReader.loadSpells();
        if (templates.isEmpty()) {
            // Fallback
            return new Spell("Ember", 40, 1, 15, 10, "Fire");
        }
        DefaultReader.SpellTemplate st = templates.get(random.nextInt(templates.size()));
        return new Spell(st.name, st.cost, st.level, st.damage, st.manaCost, st.type);
    }

    // Get a random item of any type from new_defaults data files.
    public static Item getRandomItem(Random random) {
        int choice = random.nextInt(4); // 0=weapon, 1=armor, 2=potion, 3=spell

        if (choice == 0) {
            return getRandomWeapon(random);
        } else if (choice == 1) {
            return getRandomArmor(random);
        } else if (choice == 2) {
            return getRandomPotion(random);
        } else {
            return getRandomSpell(random);
        }
    }

    // Generate a random market inventory with count items from new_defaults
    public static List<Item> generateRandomInventory(Random random, int count) {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(getRandomItem(random));
        }
        return list;
    }
    
    // Get all items that match or are below the hero's level
    public static List<Item> getAllItemsForLevel(int heroLevel, Random random) {
        List<Item> items = new ArrayList<>();
        
        // Load all items from templates
        List<DefaultReader.WeaponTemplate> weapons = DefaultReader.loadWeapons();
        List<DefaultReader.ArmorTemplate> armors = DefaultReader.loadArmor();
        List<DefaultReader.PotionTemplate> potions = DefaultReader.loadPotions();
        List<DefaultReader.SpellTemplate> spells = DefaultReader.loadSpells();
        
        // Add weapons at or below hero level
        for (DefaultReader.WeaponTemplate wt : weapons) {
            if (wt.level == heroLevel) {
                items.add(new Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands));
            }
        }
        
        // Add armors at or below hero level
        for (DefaultReader.ArmorTemplate at : armors) {
            if (at.level == heroLevel) {
                items.add(new Armor(at.name, at.cost, at.level, at.reduction));
            }
        }
        
        // Add potions at or below hero level
        for (DefaultReader.PotionTemplate pt : potions) {
            if (pt.level == heroLevel) {
                items.add(new Potion(pt.name, pt.cost, pt.level, pt.increase, pt.affected));
            }
        }
        
        // Add spells at or below hero level
        for (DefaultReader.SpellTemplate st : spells) {
            if (st.level == heroLevel) {
                items.add(new Spell(st.name, st.cost, st.level, st.damage, st.manaCost, st.type));
            }
        }
        
        return items;
    }
}