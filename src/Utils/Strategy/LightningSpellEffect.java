package src.Utils.Strategy;

import src.Monster.Monster;
import src.Utils.Interface.SpellEffect;


  // Lightning spell effect that reduces monster's dodge chance by 10%
 
public class LightningSpellEffect implements SpellEffect {

    @Override
    public void applyEffect(Monster target) {
        target.reduceDodgeBy10Percent();
    }

    @Override
    public String getEffectDescription() {
        return "Reduces target's dodge chance by 10%";
    }
}
