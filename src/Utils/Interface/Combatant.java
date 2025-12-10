package src.Utils.Interface;

// for heroes and monsters in combat
public interface Combatant {
    int getHP();
    int getLevel();
    String getName();
    boolean isFainted();
    void takeDamage(int damage);
    double getDodgeChance();
    int computeAttackDamage();
}
