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
    private int currentHeroTurn;
    
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
        currentHeroTurn = 0;
 
        while (running) {
            for (int i = 0; i < heroes.size(); i++) {
                Hero hero = heroes.get(i);
                
                currentHeroTurn = i;
                board.printBoard();
                Output.print("\n" + Output.BRIGHT_CYAN + "=== " + hero.getName() + "'s Turn (Hero " + (i+1) + ") ===" + Output.RESET);
                Output.printMenu("lov");
                
                boolean actionTaken = false;
                while (!actionTaken) {
                    actionTaken = Input.getInputLoV(this);
                    if (!actionTaken) {
                        return;
                    }
                }
            }
            
            Output.print("\n" + Output.BRIGHT_RED + "=== Monsters' Turn ===" + Output.RESET);
            Output.sleep(1000);
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

    // Place heroes on the board at their selected lanes' Nexus positions
    private void placeHeroesOnBoard() {
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            String lane = heroLanes.get(i);
            
            // Determine column based on lane
            int col;
            if ("left".equals(lane)) {
                col = i % 2; // columns 0, 1
            } else if ("mid".equals(lane)) {
                col = 3 + (i % 2); // columns 3, 4
            } else { // right
                col = 6 + (i % 2); // columns 6, 7
            }
            
            // Place hero at heroes' Nexus (row 7)
            int row = 7;
            board.getTile(row, col).setHeroOccupant(hero);
            
            Output.print(hero.getName() + " placed in " + lane + " lane at (" + row + ", " + col + ")");
        }
    }
    
    // Initialize individual markets for each hero with 2 items of each type
    private void initializeHeroMarkets() {
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            addItemsToHeroInventory(hero);
            
            LoVMarket heroMarket = new LoVMarket(hero);
            
            int heroCol = (i == 0) ? 0 : (i == 1) ? 3 : 6;
            board.getTile(7, heroCol).setMarket(heroMarket);
        }
        Output.print("Individual markets created for all heroes.");
    }
    
    // Add 2 items of each type (Weapon, Armor, Potion, Spell) to hero's inventory
    private void addItemsToHeroInventory(Hero hero) {
        java.util.Random rand = new java.util.Random();
        
        // Add 2 weapons
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomWeapon(rand), 1);
        }
        
        // Add 2 armor
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomArmor(rand), 1);
        }
        
        // Add 2 potions
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomPotion(rand), 1);
        }
        
        // Add 2 spells
        for (int i = 0; i < 2; i++) {
            hero.getInventory().addItem(getRandomSpell(rand), 1);
        }
    }
    
    // Helper methods to get random items
    private src.Item.Weapon getRandomWeapon(java.util.Random random) {
        java.util.List<DefaultReader.WeaponTemplate> templates = DefaultReader.loadWeapons();
        if (templates.isEmpty()) {
            return new src.Item.Weapon("Default Sword", 50, 1, 10, 1);
        }
        DefaultReader.WeaponTemplate wt = templates.get(random.nextInt(templates.size()));
        return new src.Item.Weapon(wt.name, wt.cost, wt.level, wt.damage, wt.hands);
    }
    
    private src.Item.Armor getRandomArmor(java.util.Random random) {
        java.util.List<DefaultReader.ArmorTemplate> templates = DefaultReader.loadArmor();
        if (templates.isEmpty()) {
            return new src.Item.Armor("Default Cloth", 20, 1, 2);
        }
        DefaultReader.ArmorTemplate at = templates.get(random.nextInt(templates.size()));
        return new src.Item.Armor(at.name, at.cost, at.level, at.reduction);
    }
    
    private src.Item.Potion getRandomPotion(java.util.Random random) {
        java.util.List<DefaultReader.PotionTemplate> templates = DefaultReader.loadPotions();
        if (templates.isEmpty()) {
            return new src.Item.Potion("Default HP Potion", 20, 1, 20, "HP");
        }
        DefaultReader.PotionTemplate pt = templates.get(random.nextInt(templates.size()));
        return new src.Item.Potion(pt.name, pt.cost, pt.level, pt.increase, pt.affected);
    }
    
    private src.Item.Spell getRandomSpell(java.util.Random random) {
        java.util.List<DefaultReader.SpellTemplate> templates = DefaultReader.loadSpells();
        if (templates.isEmpty()) {
            return new src.Item.Spell("Default Ember", 40, 1, 15, 10, "Fire");
        }
        DefaultReader.SpellTemplate st = templates.get(random.nextInt(templates.size()));
        return new src.Item.Spell(st.name, st.cost, st.level, st.damage, st.manaCost, st.type);
    }
    
    public List<Hero> getHeroes() {
        return heroes;
    }
    
    public Hero getCurrentHero() {
        return heroes.get(currentHeroTurn);
    }
    
    public int getCurrentHeroTurn() {
        return currentHeroTurn;
    }
    
    public LoVBoard getLoVBoard() {
        return (LoVBoard) board;
    }
    
    public void enterMarketForCurrentHero() {
        Hero hero = getCurrentHero();
        int heroRow = -1;
        int heroCol = -1;
        
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                if (board.getTile(r, c).getHeroOccupant() == hero) {
                    heroRow = r;
                    heroCol = c;
                    break;
                }
            }
        }
        
        if (heroRow != 7) {
            Output.print(hero.getName() + " must be at their Nexus (row 7) to access the market.");
            Output.sleep(2000);
            return;
        }
        
        Market market = board.getTile(heroRow, heroCol).getMarket();
        if (market == null) {
            Output.print("No market available at this location.");
            Output.sleep(2000);
            return;
        }
        
        market.start(user);
    }

}
