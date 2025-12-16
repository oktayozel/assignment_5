package src.Hero;
import src.Inventory.Inventory;
import src.Item.Weapon;
import src.Utils.Default.DefaultReader;
import src.Utils.Strategy.WarriorAttackStrategy;

import java.util.List;

// warrior favors strength and agility
public class Warrior extends Hero {
    public Warrior(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, inventory);
        this.heroClass = "Warrior";
        setAttackStrategy(new WarriorAttackStrategy());
    }
    
    // apply favored attribute boosts strength times 0.5 and agility times 0.5
    @Override
    protected void applyFavoredAttributeBoosts() {
        setStrength((int)Math.max(1, Math.round(getStrength() * 1.05)));
        setAgility((int)Math.max(1, Math.round(getAgility() * 1.05)));
    }
    
    // equips default weapon Axe for warrior
    public void equipDefaultWeapon() {
        List<DefaultReader.WeaponTemplate> weapons = DefaultReader.loadWeapons();
        for (DefaultReader.WeaponTemplate wt : weapons) {
            if (wt.name.equals("Axe")) {
                Weapon weapon = new Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands);
                this.getInventory().addItem(weapon, 1);
                this.setEquippedWeapon(weapon);
                return;
            }
        }
    }
}


