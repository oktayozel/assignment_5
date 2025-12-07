package src.Interface;

public interface Repairable {
    boolean isBroken();
    void repair();
    int getRepairCost();
    String getName();
}
