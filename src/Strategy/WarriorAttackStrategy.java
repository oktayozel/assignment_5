package src.Strategy;

import src.Interface.AttackStrategy;
import src.Hero.Hero;
import src.Item.Weapon;

// warrior gets 20 percent more from strength
public class WarriorAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(Hero hero, Weapon weapon, boolean usingTwoHands) {
        int weaponDamage = (weapon != null && !weapon.isBroken()) ? weapon.getDamage() : 0;
        double multiplier = usingTwoHands ? 1.5 : 1.0;
        double raw = (hero.getStrength() * 1.2 + weaponDamage) * 0.05 * multiplier;
        return Math.max(1, (int)Math.round(raw));
    }
}
