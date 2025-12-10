package src.Market;

import src.Core.User;
import src.Hero.Hero;
import src.Item.Item;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Inventory.InventoryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// market for buying selling items
public class Market {
    private final Random random = new Random();
    private final List<Item> items;              // primary stock
    private final List<Item> secondHandItems;    // items sold by heroes

    public Market() {
        items = MarketItemTemplates.generateRandomInventory(random, 10); // generate 10 random items
        secondHandItems = new ArrayList<>();
    }

    public List<Item> getItems() { return items; }
    public List<Item> getSecondHandItems() { return secondHandItems; }

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
        // Add to hero inventory
        
        hero.getInventory().addItem(item, 1);

        Output.print("Purchased: " + item.getName() + "\n");
        return true;
    }

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
        // Add to second-hand stock (a copy reference; treat each sale as single unit)
        secondHandItems.add(item);
        Output.print("Sold: " + item.getName() + " for " + sellPrice + " gold.\n");
        return true;
    }
    
    public boolean repairItem(Hero hero, Item item) {
        if (hero == null || item == null) {
            return false;
        }
        
        // Check if item implements Repairable interface
        if (!(item instanceof src.Utils.Interface.Repairable)) {
            Output.print("This item cannot be repaired.\n");
            return false;
        }
        
        src.Utils.Interface.Repairable repairable = (src.Utils.Interface.Repairable) item;
        
        // Check if item is broken
        if (!repairable.isBroken()) {
            Output.print(repairable.getName() + " is not broken and doesn't need repair.\n");
            return false;
        }
        
        // Calculate repair cost using interface method
        int repairCost = repairable.getRepairCost();
        if (!hero.spendGold(repairCost)) {
            Output.print("Not enough gold to repair. Cost: " + repairCost + " gold.\n");
            return false;
        }
        
        // Repair the item
        repairable.repair();
        
        Output.print("Repaired: " + repairable.getName() + " for " + repairCost + " gold.\n");
        return true;
    }

    // market loop
    public void start(User user) {
        if (user == null) {
            return;
        }
        user.setInMarket(true);
        boolean running = true;
        while (running) {
            Output.displayMarket(this);
            Output.printMarketMenu();
            running = Input.getMarketInput(this, user);
        }
        user.setInMarket(false);
    }
}
