package src.Item;

import src.Utils.Interface.Tradeable;

// base class for all items
abstract public class Item implements Tradeable {
    private String name;
    private int price;
    private int level;


    // holds item info
    public Item(String name, int price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }
    // getters 
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
