package src.Hero;
import src.Inventory.Inventory;
import src.Item.Weapon;
import src.Utils.Default.DefaultReader;
import src.Utils.Strategy.SorcererAttackStrategy;

import java.util.List;

// sorcerer favors dexterity and agility
public class Sorcerer extends Hero {
    public Sorcerer(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, inventory);
        this.heroClass = "Sorcerer";
        setAttackStrategy(new SorcererAttackStrategy());
    }
    
    // apply favored attribute boosts dexterity times 0.5 and agility times 0.5
    @Override
    protected void applyFavoredAttributeBoosts() {
        setDexterity((int)Math.max(1, Math.round(getDexterity() * 1.05)));
        setAgility((int)Math.max(1, Math.round(getAgility() * 1.05)));
    }
    

    // equips default weapon Scythe for sorcerer
    public void equipDefaultWeapon() {
        List<DefaultReader.WeaponTemplate> weapons = DefaultReader.loadWeapons();
        for (DefaultReader.WeaponTemplate wt : weapons) {
            if (wt.name.equals("Scythe")) {
                Weapon weapon = new Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands);
                this.getInventory().addItem(weapon, 1);
                this.setEquippedWeapon(weapon);
                return;
            }
        }
    }
}
