package src.Item;

import src.Utils.Interface.Equippable;
import src.Utils.Interface.Repairable;


// Weapon item that can be equipped by heroes
public class Weapon extends Item implements Equippable, Repairable {
    private int damage;
    private int hands;
    private int durability;
    private int maxDurability;

    // constructor
    public Weapon(String name, int price, int level, int damage, int hands) {
        super(name, price, level);
        this.damage = damage;
        this.hands = hands;
        this.maxDurability = 10;
        this.durability = maxDurability;
    }

    // getters and utility methods
    public int getDamage() {
        return damage;
    }

    
    public int getHands() {
        return hands;
    }
    
    public int getDurability() {
        return durability;
    }
    
    public int getMaxDurability() {
        return maxDurability;
    }
    
    public boolean isBroken() {
        return durability <= 0;
    }
    
    public void useDurability() {
        durability--;
    }
    
    public void repair() {
        durability = maxDurability;
    }
    
    public int getRepairCost() {
        return getPrice() / 2;
    }

    // overrides toString method
    public String toString() {
        String durabilityStr = isBroken() ? "(BROKEN)" : "(" + durability + "/" + maxDurability + ")";
        return String.format("[Weapon] %s %s (Price: %d, Level: %d, Damage: %d, Hands: %d)", getName(), durabilityStr, getPrice(), getLevel(), damage, hands);
    }
}
