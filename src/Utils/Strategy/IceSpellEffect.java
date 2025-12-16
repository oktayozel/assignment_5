package src.Utils.Strategy;

import src.Monster.Monster;
import src.Utils.Interface.SpellEffect;

 
 // Ice spell effect that reduces monster's damage output by 10%
 
public class IceSpellEffect implements SpellEffect {

    @Override
    public void applyEffect(Monster target) {
        target.reduceBaseDamageBy10Percent();
    }

    @Override
    public String getEffectDescription() {
        return "Reduces target's damage by 10%";
    }
}
