package src.Utils.Interface;

import src.Hero.Hero;
import src.Item.Weapon;

// strategy for attack damage
public interface AttackStrategy {
    int calculateDamage(Hero hero, Weapon weapon, boolean usingTwoHands);
}
