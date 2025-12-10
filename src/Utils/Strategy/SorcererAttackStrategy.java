package src.Utils.Strategy;

import src.Hero.Hero;
import src.Item.Weapon;
import src.Utils.Interface.AttackStrategy;

// sorcerer uses 70 percent strength 30 percent dexterity
public class SorcererAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(Hero hero, Weapon weapon, boolean usingTwoHands) {
        int weaponDamage = (weapon != null && !weapon.isBroken()) ? weapon.getDamage() : 0;
        double multiplier = usingTwoHands ? 1.5 : 1.0;
        double raw = (hero.getStrength() * 0.7 + hero.getDexterity() * 0.3 + weaponDamage) * 0.05 * multiplier;
        return Math.max(1, (int)Math.round(raw));
    }
}
