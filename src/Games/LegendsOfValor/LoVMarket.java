package src.Games.LegendsOfValor;

import src.Core.User;
import src.Hero.Hero;
import src.Item.Item;
import src.Market.Market;
import src.Market.MarketItemTemplates;
import src.Utils.IO.Output;
import src.Utils.IO.Input;
import java.util.ArrayList;
import java.util.List;


// hero spesific market for Legends of Valor
public class LoVMarket extends Market {
    private final Hero hero;
    
    public LoVMarket(Hero hero) {
        super();
        this.hero = hero;
    }
    
    public Hero getHero() {
        return hero;
    }

    @Override
    public void start(User user) {
        if (user == null || hero == null) {
            return;
        }
        
        // Populate market items based on hero's level
        populateMarketItems();
        
        user.setInMarket(true);
        boolean running = true;
        while (running) {
            Output.displayLoVMarketHeader(hero);
            
            Output.print("\n--- Available Items ---");
            if (items.isEmpty()) {
                Output.print("\nNo items available.");
            } else {
                for (int i = 0; i < items.size(); i++) {
                    Output.print("\n" + (i+1) + ". " + items.get(i).toString());
                }
            }
            
            Output.print("\n\n--- Your Inventory ---");
            Output.print("\n" + hero.getInventory().toString());
            
            Output.displayLoVMarketMenu();
            
            String choice = Input.getLoVMarketInput();
            
            if (choice.equals("B")) {
                handleBuy();
            } else if (choice.equals("S")) {
                handleSell();
            } else if (choice.equals("V")) {
                Output.clearScreen();
                Output.print("\n" + hero.toString());
                Output.print("\nPress Enter to continue...");
                Input.getUsername();
            } else if (choice.equals("E")) {
                running = false;
            }
        }
        user.setInMarket(false);
    }
    
    private void populateMarketItems() {
        items.clear();
        int heroLevel = hero.getLevel();
        
        // Load all items of the hero's level
        List<Item> allItems = MarketItemTemplates.getAllItemsForLevel(heroLevel, random);
        items.addAll(allItems);
    }
    
    private void handleBuy() {
        if (items.isEmpty()) {
            Output.print("No items to buy.");
            Output.sleep(1500);
            return;
        }
        
        Output.print("\nSelect item to buy (1-" + items.size() + ") or 0 to cancel: ");
        int itemChoice = Input.readInt(0, items.size());
        
        if (itemChoice == 0) return;
        
        buyItem(hero, items.get(itemChoice - 1));
        Output.sleep(1500);
    }
    
    private void handleSell() {
        if (hero.getInventory().getEntries().isEmpty()) {
            Output.print("Your inventory is empty.");
            Output.sleep(1500);
            return;
        }
        
        // Display inventory items with numbers
        Output.print("\n--- Your Inventory ---");
        List<src.Inventory.InventoryEntry> entries = hero.getInventory().getEntries();
        for (int i = 0; i < entries.size(); i++) {
            src.Inventory.InventoryEntry entry = entries.get(i);
            Output.print("\n" + (i + 1) + ". " + entry.getItem().toString() + " x" + entry.getQuantity());
        }
        
        Output.print("\n\nSelect item to sell (1-" + entries.size() + ") or 0 to cancel: ");
        int itemChoice = Input.readInt(0, entries.size());
        
        if (itemChoice == 0) return;
        
        // Get the item name from the selected entry
        String itemName = entries.get(itemChoice - 1).getItem().getName();
        sellItem(hero, itemName);
        Output.sleep(1500);
    }
}
