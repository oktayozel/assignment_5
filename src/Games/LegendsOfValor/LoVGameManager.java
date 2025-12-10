package src.Games.LegendsOfValor;

import src.Hero.Hero;
import src.Hero.Paladin;
import src.Hero.Sorcerer;
import src.Hero.Warrior;
import src.Inventory.Inventory;
import src.Utils.Default.DefaultReader;
import java.util.ArrayList;
import java.util.List;
import src.Core.GameManager;
import src.Utils.Statistics.Statistics;
import src.Utils.IO.Input;;
import src.Utils.IO.Output;
import src.Market.Market;
import src.Core.User;

public class LoVGameManager extends GameManager {
    private List<Hero> heroes;
    private List<String> heroLanes;
    
    private User user;

    public LoVGameManager(Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        this.heroes = new ArrayList<>();
        this.heroLanes = new ArrayList<>();
        
        Output.gameInitializationMessage("lov");
        Output.someSpace();

        this.user = new User(Input.getUsername());
        initializeHeroes();
        this.board = new LoVBoard();
        while(!board.isBoardPlayable()){
            this.board = new LoVBoard();
        }
        placeHeroesOnBoard();
        initializeHeroMarkets();
    }

    @Override
    public void start() {
        Output.displaySecondWelcomeMessage(user);
        boolean running = true;
 
        while (running) {
            board.printBoard(-1,-1);
            Output.printMenu("lov");
            running = Input.getInputLoV(this);

        }

        Output.clearScreen();
        Output.displayStatistics(statistics);
        System.out.println(Output.BRIGHT_YELLOW + "Press ENTER to continue..." + Output.RESET);
        Input.waitForEnter();
        Output.print("Thanks for playing!");
    }


    public void initializeHeroes() {
        String[] laneNames = {"left", "mid", "right"};
        for (int i = 1; i <= 3; i++) {
            Output.print("Select type for Hero " + i + " (1: Warrior, 2: Sorcerer, 3: Paladin): ");
            int heroType = Input.readInt(1, 3);
            String classPlural = heroType == 1 ? "Warriors" : heroType == 2 ? "Sorcerers" : "Paladins";
            
            DefaultReader.HeroTemplate chosen = DefaultReader.getRandomHero(classPlural);
            while (chosen == null) {
                Output.print("No hero found for type " + classPlural + ". Please select again (1-3): ");
                heroType = Input.readInt(1, 3);
                classPlural = heroType == 1 ? "Warriors" : heroType == 2 ? "Sorcerers" : "Paladins";
                chosen = DefaultReader.getRandomHero(classPlural);
            }

            Inventory inv = new Inventory();
            String romanNumber = (i==1?"I": i==2?"II":"III");
            Hero hero;
            
            if ("Warriors".equals(classPlural)) {
                hero = new Warrior(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
            } else if ("Sorcerers".equals(classPlural)) {
                hero = new Sorcerer(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
            } else {
                hero = new Paladin(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
            }
            
            heroes.add(hero);
            
            Output.print("Select lane for Hero " + i + " (1: left, 2: mid, 3: right): ");
            int laneChoice = Input.readInt(1, 3);
            heroLanes.add(laneNames[laneChoice-1]);
        }
    }

    private void placeHeroesOnBoard() {
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            String lane = heroLanes.get(i);
            int col;
            if ("left".equals(lane)) {
                col = i % 2;
            } else if ("mid".equals(lane)) {
                col = 3 + (i % 2);
            } else {
                col = 6 + (i % 2);
            }
            
            int row = 7;
            board.getTile(row, col).setHeroOccupant(hero);
            
            Output.print(hero.getName() + " placed in " + lane + " lane at (" + row + ", " + col + ")");
        }
    }
    
    private void initializeHeroMarkets() {
        for (Hero hero : heroes) {
            addItemsToHeroInventory(hero);
        }
        Output.print("Individual market items initialized for all heroes.");
    }
    
    private void addItemsToHeroInventory(Hero hero) {
        java.util.Random rand = new java.util.Random();
        
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomWeapon(rand), 1);
        }
        
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomArmor(rand), 1);
        }
        
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomPotion(rand), 1);
        }
        
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomSpell(rand), 1);
        }
    }
    
    private src.Item.Weapon getRandomWeapon(java.util.Random random) {
        return new src.Item.Weapon("Sword", 500, 1, 800, 1);
    }
    
    private src.Item.Armor getRandomArmor(java.util.Random random) {
        return new src.Item.Armor("Platinum Shield", 150, 1, 200);
    }
    
    private src.Item.Potion getRandomPotion(java.util.Random random) {
        return new src.Item.Potion("Healing Potion", 250, 1, 100, "Health");
    }
    
    private src.Item.Spell getRandomSpell(java.util.Random random) {
        return new src.Item.Spell("Flame Tornado", 700, 1, 850, 300, "Fire");
    }

    @Override
    public void handleTileEvent() {
        return;
    }
}
