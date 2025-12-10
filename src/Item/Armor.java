package src.Item;

import src.Utils.Interface.Equippable;
import src.Utils.Interface.Repairable;



// armor item
public class Armor extends Item implements Equippable, Repairable {
    private int damageReduction;
    private int durability;
    private int maxDurability;

    // constructor
    public Armor(String name, int price, int level, int damageReduction) {
        super(name, price, level);
        this.damageReduction = damageReduction;
        this.maxDurability = 10;
        this.durability = maxDurability;
    }
    // getters
    public int getDamageReduction() {
        return damageReduction;
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
    
    public String toString() {
        String durabilityStr = isBroken() ? "(BROKEN)" : "(" + durability + "/" + maxDurability + ")";
        return String.format("[Armor] %s %s (Price: %d, Level: %d, Damage Reduction: %d)", getName(), durabilityStr, getPrice(), getLevel(), damageReduction);

    }
}
