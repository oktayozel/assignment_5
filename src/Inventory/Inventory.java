package src.Inventory;

import java.util.ArrayList;
import java.util.List;
import src.Item.Item;

public class Inventory {

    private List<InventoryEntry> entries;

    public Inventory(List<InventoryEntry> entries) {
        this.entries = entries;
    }

    public Inventory() {
        this.entries = new ArrayList<>();
    }

    // get all entries
    public List<InventoryEntry> getEntries() {
        return entries;
    }

    
    // Add an item to the inventory.
    // If the item already exists, increase its quantity.
    
    public void addItem(Item item, int quantity) {
        InventoryEntry existing = getEntryByName(item.getName());
        if (existing != null) {
            existing.increaseQuantity(quantity);
        } else {
            entries.add(new InventoryEntry(item, quantity));
        }
    }

     
    // Completely remove an item from the inventory by name.
     
    public void removeItemByName(String name) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getItem().getName().equals(name)) {
                entries.remove(i);
                return;
            }
        }
    }

     
    // Get the InventoryEntry for a given item name.
     
    public InventoryEntry getEntryByName(String name) {
        for (InventoryEntry entry : entries) {
            if (entry.getItem().getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }


    //
    //. Decrease quantity of an item by name.
    //If quantity drops to 0 or below, the entry is removed.
    //
    public void decreaseItemQuantity(String itemName, int amount) {
        for (int i = 0; i < entries.size(); i++) {
            InventoryEntry entry = entries.get(i);
            if (entry.getItem().getName().equals(itemName)) {
                entry.decreaseQuantity(amount);
                if (entry.getQuantity() <= 0) {
                    entries.remove(i);
                }
                return;
            }
        }
    }
    public void toStringList() {
        for (InventoryEntry entry : entries) {
            System.out.println(entry.getItem().getName() + " x" + entry.getQuantity());
        }
    }


}
