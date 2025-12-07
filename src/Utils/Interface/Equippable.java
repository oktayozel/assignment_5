package src.Interface;

// for weapons and armor
public interface Equippable {
    String getName();
    int getLevel();
    boolean isBroken();
    void useDurability();
    void repair();
    int getDurability();
    int getMaxDurability();
}
