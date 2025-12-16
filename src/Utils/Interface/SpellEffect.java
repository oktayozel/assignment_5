package src.Utils.Interface;

import src.Monster.Monster;

/**
 * Interface for spell effects using Strategy Pattern
 * Each spell type implements its own effect on monsters
 */
public interface SpellEffect {
    
    /**
     * Apply the spell's special effect to the target monster
     * Effects reduce monster attributes by a percentage
     * @param target The monster to apply the effect to
     */
    void applyEffect(Monster target);
    
    /**
     * Get a description of what this effect does
     * @return Description string
     */
    String getEffectDescription();
}
