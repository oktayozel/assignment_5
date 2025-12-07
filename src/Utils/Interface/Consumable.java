package src.Interface;

// for potions and spells
public interface Consumable {
    String getName();
    int getManaCost();
    boolean canBeUsed(int availableMP);
}
