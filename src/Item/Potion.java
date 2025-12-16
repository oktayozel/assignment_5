package src.Item;

import src.Utils.Interface.Consumable;


// potion item extends regular item
public class Potion extends Item implements Consumable {

    private int effectAmount;
    private String effectType;


    // constructor
    public Potion(String name, int price, int level, int effectAmount, String effectType) {
        super(name, price, level);
        this.effectAmount = effectAmount;
        this.effectType = effectType;
    }

    public int getEffectAmount() {
        return effectAmount;
    }
    
    // Potions don't require mana
    public int getManaCost() {
        return 0;
    }
    
    public boolean canBeUsed(int availableMP) {
        return true; // potions can always be used
    }

    public String getEffectType() {
        return effectType;
    }
    public String toString() {
        return String.format("[Potion] %s (Price: %d, Level: %d, Effect Type: %s, Effect Amount: %d)", getName(), getPrice(), getLevel(), effectType, effectAmount);
    }
}
