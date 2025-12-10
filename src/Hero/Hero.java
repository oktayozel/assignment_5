package src.Hero;

import src.Inventory.Inventory;
import src.Item.Weapon;
import src.Utils.Interface.AttackStrategy;
import src.Utils.Interface.Combatant;
import src.Item.Armor;
import src.Item.Potion;

// hero base class
public abstract class Hero implements Combatant {
    
    // each hero class has favored attributes
    protected abstract void applyFavoredAttributeBoosts();
    private String name;
    private int level;
    private int HP;
    private int MP;
    private int strength;
    private int dexterity;
    private int agility;
    private int gold;
    private int experience;
    protected String heroClass;
    private Inventory inventory;
    private Weapon equippedWeapon;  // if the hero has equipped a weapon
    private Armor equippedArmor;   // if the hero has equipped an armor
    private boolean usingTwoHands;  // whether using a onehanded weapon with both hands
    private AttackStrategy attackStrategy;  // strategy pattern for damage calc

    public Hero(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, Inventory inventory) {
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.experience = 0;
        this.inventory = inventory;
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.usingTwoHands = false;
    }


    public void addGold(int amount) { 
        this.gold += amount;
    }

    public boolean spendGold(int amount) {
        if (amount <= 0) {
            return true;
        }
        if (amount > gold) {
            return false;
        }
        gold -= amount;
        return true;
    }
    public void setGold(int gold) { 
        this.gold = Math.max(0, gold); 
    }

    public void addExperience(int xp) { 
        if (xp > 0) {
            this.experience += xp;
        }
    }
    public void setExperience(int xp) { 
        this.experience = Math.max(0, xp); 
    }





    // calc dodge chance from agility
    public double getDodgeChance() {
        double chance = agility * 0.002; 
        if (chance < 0) {
            chance = 0;
        }
        if (chance > 0.6) {
            chance = 0.6;
        }
        return chance;
    }

    // calc attack damage
    public int computeAttackDamage() {
        // check weapon durability
        if (equippedWeapon != null) {
            if (!equippedWeapon.isBroken()) {
                // use up durability
                equippedWeapon.useDurability();
                if (equippedWeapon.isBroken()) {
                    System.out.println(name + "'s " + equippedWeapon.getName() + " has broken!");
                }
            } else {
                System.out.println(name + "'s weapon is broken! No bonus damage.");
            }
        }
        
        // use strategy to calc damage
        return attackStrategy.calculateDamage(this, equippedWeapon, usingTwoHands);
    }
    
    // set attack strategy
    protected void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    // armor reduces damage
    public int getArmorReduction() {
        return (equippedArmor != null ? equippedArmor.getDamageReduction() : 0);
    }

    // take damage
    public void takeDamage(int amount) {
        int armorReduction = 0;
        if (equippedArmor != null && !equippedArmor.isBroken()) {
            armorReduction = equippedArmor.getDamageReduction();
            // armor gets worn down
            equippedArmor.useDurability();
            if (equippedArmor.isBroken()) {
                System.out.println(name + "'s " + equippedArmor.getName() + " has broken!");
            }
        }
        int dmg = Math.max(0, amount - armorReduction);
        HP -= dmg;
        if (HP < 0) {
            HP = 0;
        }
    }


    // check if dead
    public boolean isFainted() { return HP <= 0; }

    public void healHP(int amount) { 
        HP += amount;
    }

    public boolean spendMP(int amount) {
        if (amount < 0) {
            return false;
        }
        if (MP < amount) {
            return false;
        }
        MP -= amount;
        return true;
    }
    public void restoreMP(int amount) {
        MP += amount;
    }
    
    protected void setStrength(int strength) {
        this.strength = strength;
    }
    
    protected void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    
    protected void setAgility(int agility) {
        this.agility = agility;
    }

    // regen 10% hp and mp each round
    public void regenAfterRound() {
        if (!isFainted()) {
            HP = (int)Math.round(HP * 1.1);
            MP = (int)Math.round(MP * 1.1);
        }
    }


    public void reviveHalf() {
        if (isFainted()) {
            int baseHP = Math.max(1, level * 100);
            HP = Math.max(1, baseHP / 2);
            MP = Math.max(1, MP / 2);
        }
    }

    public void applyPotion(Potion p) {
        if (p == null) {
            return;
        }
        String type = p.getEffectType();
        int amount = p.getEffectAmount();
        if (type == null) {
            return;
        }
        switch (type.toUpperCase()) {
            case "HP": healHP(amount); break;
            case "MP": restoreMP(amount); break;
            case "STRENGTH": strength += amount; break;
            case "DEXTERITY": dexterity += amount; break;
            case "AGILITY": agility += amount; break;
            case "DEFENSE": /* extend later if needed */ break;
            default: break;
        }
    }

    // level up if enough xp
    public boolean checkLevelUp() {
        boolean leveled = false;
        while (experience >= level * 10) {
            level++;
            leveled = true;

            // reset hp and boost stats
            HP = level * 100; 
            MP = (int)Math.round(MP * 1.10);
            strength = (int)Math.max(1, Math.round(strength * 1.05));
            dexterity = (int)Math.max(1, Math.round(dexterity * 1.05));
            agility = (int)Math.max(1, Math.round(agility * 1.05));

            // boost favored attributes
            applyFavoredAttributeBoosts();
        }
        if (leveled) {
            System.out.println(name + " leveled up! New level: " + level);
        }
        return leveled;
    }

    public String getName(){ 
        return name; 
    }
    public int getLevel() { 
        return level; 
    }
    public int getHP() { 
        return HP; 
    }
    public int getMP() { 
        return MP; 
    }
    public int getStrength() { 
        return strength; 
    }
    public int getDexterity() { 
        return dexterity; 
    }
    public int getAgility() { 
        return agility; 
    }
    public int getGold() { 
        return gold; 
    }
    public int getExperience() { 
        return experience; 
    }
    public Inventory getInventory() { 
        return inventory; 
    }
    public String getHeroClass() { 
        return heroClass; 
    }
    public Weapon getEquippedWeapon() { 
        return equippedWeapon; 
    }
    public Armor getEquippedArmor() { 
        return equippedArmor; 
    }
    public void setEquippedWeapon(Weapon weapon) { 
        this.equippedWeapon = weapon;
        if (weapon != null && weapon.getHands() == 2) {
            this.usingTwoHands = true;
        } else {
            this.usingTwoHands = false;
        }
    }
    
    public void setEquippedWeapon(Weapon weapon, boolean useBothHands) {
        this.equippedWeapon = weapon;
        if (weapon == null) {
            this.usingTwoHands = false;
        } else if (weapon.getHands() == 2) {
            // two handed weapons need both hands
            this.usingTwoHands = true;
        } else {
            // one handed can use one or two
            this.usingTwoHands = useBothHands;
        }
    }
    
    public void setEquippedArmor(Armor armor) { 
        this.equippedArmor = armor; 
    }
    
    public boolean isUsingTwoHands() {
        return usingTwoHands;
    }

    // get weapons from inventory
    public java.util.List<Weapon> getAvailableWeapons() {
        java.util.List<Weapon> weapons = new java.util.ArrayList<>();
        for (src.Inventory.InventoryEntry entry : inventory.getEntries()) {
            if (entry.getItem() instanceof Weapon) {
                weapons.add((Weapon) entry.getItem());
            }
        }
        return weapons;
    }

    // get armor from inventory
    public java.util.List<Armor> getAvailableArmor() {
        java.util.List<Armor> armors = new java.util.ArrayList<>();
        for (src.Inventory.InventoryEntry entry : inventory.getEntries()) {
            if (entry.getItem() instanceof Armor) {
                armors.add((Armor) entry.getItem());
            }
        }
        return armors;
    }

    // get potions from inventory
    public java.util.List<Potion> getAvailablePotions() {
        java.util.List<Potion> potions = new java.util.ArrayList<>();
        for (src.Inventory.InventoryEntry entry : inventory.getEntries()) {
            if (entry.getItem() instanceof Potion) {
                potions.add((Potion) entry.getItem());
            }
        }
        return potions;
    }

    // equip weapon by index
    public boolean equipWeaponByIndex(int index) {
        java.util.List<Weapon> weapons = getAvailableWeapons();
        if (index < 0 || index >= weapons.size()) {
            return false;
        }
        this.equippedWeapon = weapons.get(index);
        return true;
    }

    // equip armor by index
    public boolean equipArmorByIndex(int index) {
        java.util.List<Armor> armors = getAvailableArmor();
        if (index < 0 || index >= armors.size()) {
            return false;
        }
        this.equippedArmor = armors.get(index);
        return true;
    }

    // use potion from inventory
    public boolean usePotionByIndex(int index) {
        java.util.List<Potion> potions = getAvailablePotions();
        if (index < 0 || index >= potions.size()) {
            return false;
        }
        Potion potion = potions.get(index);
        applyPotion(potion);
        
        // remove from inventory
        src.Inventory.InventoryEntry entry = inventory.getEntryByName(potion.getName());
        if (entry != null) {
            entry.decreaseQuantity(1);
            if (entry.getQuantity() <= 0) {
                inventory.removeItemByName(potion.getName());
            }
        }
        return true;
    }

    public abstract void equipDefaultWeapon();

    @Override
    public String toString() {
        return "Hero{" +
                "Name='" + name + '\'' +
                ", Level=" + level +
                ", HP=" + HP +
                ", MP=" + MP +
                ", EXP=" + experience +
                ", Gold=" + gold +
                '}';
    }

}
