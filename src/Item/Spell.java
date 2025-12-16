package src.Item;

import src.Utils.Interface.Consumable;
import src.Utils.Interface.SpellEffect;
import src.Utils.Strategy.FireSpellEffect;
import src.Utils.Strategy.IceSpellEffect;
import src.Utils.Strategy.LightningSpellEffect;
import src.Monster.Monster;


 // Spell item that can be cast by heroes
 // Uses Strategy Pattern for spell effects
 
public class Spell extends Item implements Consumable {
    private int damage;
    private int manaCost;
    private String spellType;
    private SpellEffect spellEffect;

    // constructor
    public Spell(String name, int price, int level, int damage, int manaCost, String spellType) {
        super(name, price, level);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
        
        // Assign appropriate spell effect based on type (Strategy Pattern)
        switch (spellType.toUpperCase()) {
            case "FIRE":
                this.spellEffect = new FireSpellEffect();
                break;
            case "ICE":
                this.spellEffect = new IceSpellEffect();
                break;
            case "LIGHTNING":
                this.spellEffect = new LightningSpellEffect();
                break;
            default:
                throw new IllegalArgumentException("Unknown spell type: " + spellType);
        }
    }


    //getters 
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
    
    // 
    // Get the spell effect strategy
    // 
    public SpellEffect getSpellEffect() {
        return spellEffect;
    }
    
    // apply effect function
    public void applyEffect(Monster target) {
        spellEffect.applyEffect(target);
    }
    
    // get effect description
    public String getEffectDescription() {
        return spellEffect.getEffectDescription();
    }
    
    // overrides toString method
    public String toString() {
        return String.format("[Spell] %s (Price: %d, Level: %d, Spell Type: %s, Damage: %d, Mana Cost: %d)", 
                           getName(), getPrice(), getLevel(), spellType, damage, manaCost);
    }
}
