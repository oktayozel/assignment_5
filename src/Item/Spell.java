package src.Item;

import src.Utils.Interface.Consumable;
import src.Utils.Interface.SpellEffect;
import src.Utils.Strategy.FireSpellEffect;
import src.Utils.Strategy.IceSpellEffect;
import src.Utils.Strategy.LightningSpellEffect;
import src.Monster.Monster;

/**
 * Spell item that can be cast by heroes
 * Uses Strategy Pattern for spell effects
 */
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
    
    /**
     * Get the spell effect strategy
     * @return The SpellEffect implementation for this spell type
     */
    public SpellEffect getSpellEffect() {
        return spellEffect;
    }
    
    /**
     * Apply the spell's special effect to a target monster
     * @param target The monster to apply the effect to
     */
    public void applyEffect(Monster target) {
        spellEffect.applyEffect(target);
    }
    
    /**
     * Get description of what this spell's effect does
     * @return Effect description
     */
    public String getEffectDescription() {
        return spellEffect.getEffectDescription();
    }
    
    public String toString() {
        return String.format("[Spell] %s (Price: %d, Level: %d, Spell Type: %s, Damage: %d, Mana Cost: %d)", 
                           getName(), getPrice(), getLevel(), spellType, damage, manaCost);
    }
}
