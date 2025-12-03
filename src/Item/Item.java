package src.Item;

import src.Interface.Tradeable;

// base class for all items
abstract public class Item implements Tradeable {
    private String name;
    private int price;
    private int level;

    public Item(String name, int price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }
    
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getLevel() {
        return level;   
    }

}
