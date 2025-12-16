package src.Utils.Strategy;

import src.Monster.Monster;
import src.Utils.Interface.SpellEffect;

/**
 * Fire spell effect that reduces monster's defense by 10%
 */
public class FireSpellEffect implements SpellEffect {

    @Override
    public void applyEffect(Monster target) {
        target.reduceDefenseBy10Percent();
    }

    @Override
    public String getEffectDescription() {
        return "Reduces target's defense by 10%";
    }
}
