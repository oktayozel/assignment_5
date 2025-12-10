package src.Utils.Strategy;

import src.Hero.Hero;
import src.Item.Weapon;
import src.Utils.Interface.AttackStrategy;

// paladin balanced with 10 percent chance to heal 5 percent of damage
public class PaladinAttackStrategy implements AttackStrategy {
    @Override
    public int calculateDamage(Hero hero, Weapon weapon, boolean usingTwoHands) {
        int weaponDamage = (weapon != null && !weapon.isBroken()) ? weapon.getDamage() : 0;
        double multiplier = usingTwoHands ? 1.5 : 1.0;
        double raw = (hero.getStrength() + weaponDamage) * 0.05 * multiplier;
        int damage = Math.max(1, (int)Math.round(raw));
        
        if (Math.random() < 0.1) {
            int healAmount = Math.max(1, (int)(damage * 0.05));
            hero.healHP(healAmount);
            System.out.println(hero.getName() + " heals " + healAmount + " HP from righteous strike!");
        }
        
        return damage;
    }
}
