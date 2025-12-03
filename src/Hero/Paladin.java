package src.Hero;
import src.Inventory.Inventory;
import src.Item.Weapon;
import src.Default.DefaultReader;
import java.util.List;
import src.Strategy.PaladinAttackStrategy;

// paladin favors strength and dexterity
public class Paladin extends Hero{
    public Paladin(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, inventory);
        this.heroClass = "Paladin";
        setAttackStrategy(new PaladinAttackStrategy());
        
    }
    
    @Override
    protected void applyFavoredAttributeBoosts() {
        setStrength((int)Math.max(1, Math.round(getStrength() * 1.05)));
        setDexterity((int)Math.max(1, Math.round(getDexterity() * 1.05)));
    }
    
    public void equipDefaultWeapon() {
        List<DefaultReader.WeaponTemplate> weapons = DefaultReader.loadWeapons();
        for (DefaultReader.WeaponTemplate wt : weapons) {
            if (wt.name.equals("Sword")) {
                Weapon weapon = new Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands);
                this.getInventory().addItem(weapon, 1);
                this.setEquippedWeapon(weapon);
                return;
            }
        }
    }
}
