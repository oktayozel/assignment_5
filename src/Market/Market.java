package src.Market;

import src.Core.User;
import src.Hero.Hero;
import src.Item.Item;
import src.Utils.IO.Output;
import src.Inventory.InventoryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// market class for buying, selling, repairing items
public abstract class Market {
    protected final Random random = new Random();
    protected final List<Item> items;
    protected final List<Item> secondHandItems;

    
    // constructor
    public Market() {
        items = new ArrayList<>();
        secondHandItems = new ArrayList<>();
    }

    // getters
    public List<Item> getItems() { return items; }
    public List<Item> getSecondHandItems() { return secondHandItems; }

    protected void addItem(Item item) {
        items.add(item);
    }


    // buy item for hero
    public boolean buyItem(Hero hero, Item item) {
        if (hero == null || item == null) {
            return false;
        }
        if (hero.getLevel() < item.getLevel()) {
            Output.print("Hero level too low for this item.\n");
            return false;
        }
        if (!hero.spendGold(item.getPrice())) {
            Output.print("Not enough gold.\n");
            return false;
        }
        
        hero.getInventory().addItem(item, 1);
        Output.print("Purchased: " + item.getName() + "\n");
        return true;
    }


    // gets items from the inventory first lists them and assigns ids to them and sells based on id
    public boolean sellItem(Hero hero, String itemName) {
        if (hero == null || itemName == null || itemName.isEmpty()) {
            return false;
        }
        InventoryEntry entry = hero.getInventory().getEntryByName(itemName);
        if (entry == null) {
            Output.print("Item not found in inventory.\n");
            return false;
        }
        Item item = entry.getItem();
        int sellPrice = item.getPrice() / 2;
        hero.addGold(sellPrice);
        if (entry.getQuantity() > 1) {
            entry.decreaseQuantity(1);
        } else {
            hero.getInventory().removeItemByName(itemName);
        }
        secondHandItems.add(item);
        Output.print("Sold: " + item.getName() + " for " + sellPrice + " gold.\n");
        return true;
    }
    
    // repair item for hero
    public boolean repairItem(Hero hero, Item item) {
        if (hero == null || item == null) {
            return false;
        }
        
        if (!(item instanceof src.Utils.Interface.Repairable)) {
            Output.print("This item cannot be repaired.\n");
            return false;
        }
        
        src.Utils.Interface.Repairable repairable = (src.Utils.Interface.Repairable) item;
        
        if (!repairable.isBroken()) {
            Output.print(repairable.getName() + " is not broken and doesn't need repair.\n");
            return false;
        }
        
        int repairCost = repairable.getRepairCost();
        if (!hero.spendGold(repairCost)) {
            Output.print("Not enough gold to repair. Cost: " + repairCost + " gold.\n");
            return false;
        }
        
        repairable.repair();
        Output.print("Repaired: " + repairable.getName() + " for " + repairCost + " gold.\n");
        return true;
    }

    public abstract void start(User user);
}
