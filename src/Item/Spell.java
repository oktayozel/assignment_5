package src.Item;

import src.Utils.Interface.Consumable;


// spell item
public class Spell extends Item implements Consumable {
    private int damage;
    private int manaCost;
    private String spellType;

    // constructor
    public Spell(String name, int price, int level, int damage, int manaCost, String spellType) {
        super(name, price, level);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
    
    public boolean canBeUsed(int availableMP) {
        return availableMP >= manaCost;
    }

    public String getSpellType() {
        return spellType;
    }
    public String toString() {
        return String.format("[Spell] %s (Price: %d, Level: %d, Spell Type: %s, Damage: %d, Mana Cost: %d)", getName(), getPrice(), getLevel(), spellType, damage, manaCost);
    }
}
