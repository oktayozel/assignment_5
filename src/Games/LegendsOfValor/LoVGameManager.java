package src.Games.LegendsOfValor;

import src.Hero.Hero;
import src.Hero.Paladin;
import src.Hero.Sorcerer;
import src.Hero.Warrior;
import src.Inventory.Inventory;
import src.Utils.Default.DefaultReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Battle.LoVCombatHandler;
import src.Core.GameManager;
import src.Core.Party;
import src.Utils.Statistics.Statistics;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Market.Market;
import src.Monster.Monster;
import src.Core.User;

public class LoVGameManager extends GameManager {
    private List<Hero> heroes;
    private List<String> heroLanes;
    private int currentHeroTurn;
    
    private User user;

    // Combat system fields
    private int round;
    private int spawnInterval;
    private boolean gameOver;
    
    private LoVCombatHandler combat;
    private Map<Hero, int[]> heroPositions;
    private Map<Monster, int[]> monsterPositions;
    private List<Monster> activeMonsters;

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
        
        this.spawnInterval = Input.getLoVDifficulty();
        this.round = 0;
        this.gameOver = false;
        
        initializeHeroes();
        this.board = new LoVBoard();
        while(!board.isBoardPlayable()){
            this.board = new LoVBoard();
        }
        placeHeroesOnBoard();
        initializeHeroMarkets();
        
        initializeCombatSystem();
    }



    // Initialize combat system -- for LoVCombatHandler
    private void initializeCombatSystem() {
        this.heroPositions = new HashMap<>();
        this.monsterPositions = new HashMap<>();
        this.activeMonsters = new ArrayList<>();
        
        // Build hero position map from board
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                Hero h = board.getTile(r, c).getHeroOccupant();
                if (h != null) {
                    int laneIndex = (c <= 1) ? 0 : (c >= 3 && c <= 4) ? 1 : 2;
                    heroPositions.put(h, new int[]{r, c, laneIndex});
                }
            }
        }
        
        // Create Party from heroes
        Party party = new Party();
        for (Hero h : heroes) {
            party.addHero(h);
        }
        
        // Create combat handler
        this.combat = new LoVCombatHandler(
            (LoVBoard) board,
            party,
            heroPositions,
            monsterPositions,
            activeMonsters
        );
        
        // Spawn initial monsters (round 0)
        Output.print("\n" + Output.BRIGHT_RED + "⚔️  MONSTERS APPROACHING! ⚔️" + Output.RESET);
        combat.spawnMonsterWave(0);
        Output.sleep(2000);
    }


    @Override
    public void start() {
        Output.displaySecondWelcomeMessage(heroes, user);
        
        while (!gameOver) {
            round++;
            Output.clearScreen();
            board.printBoard();
            Output.print("\n" + Output.BRIGHT_YELLOW + "========== ROUND " + round + " ==========" + Output.RESET);
            
            // Heroes' turn
            heroesTurn();
            if (gameOver) break;
            
            // Monsters' turn
            monstersTurn();
            if (gameOver) break;
            
            // End of round
            endOfRound();
        }
        
        handleGameEnd();
    }

    // ============================================
    // HERO TURN METHODS
    // ============================================

    // handles hero turn
    private void heroesTurn() {
        Output.print("\n" + Output.BRIGHT_CYAN + "===== HEROES' TURN =====" + Output.RESET);
        
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            currentHeroTurn = i;
            
            if (hero.isFainted()) {
                Output.print(hero.getName() + " is fainted and cannot act.");
                continue;
            }
            
            boolean turnComplete = false;
            while (!turnComplete) {
                // Display board and hero status
                Output.clearScreen();
                Output.boardBanner("lov");
                board.printBoard();

                int[] pos = heroPositions.get(hero);
                Output.displayHeroStatus(hero, pos[0], pos[1], pos[2]);
                
                Output.printMenu("lov");  
                turnComplete = Input.getInputLoV(this);
                if (gameOver) return;
            }
        }
    }    

    // Movement wrapper
    public void moveHeroWithBoard(Hero hero, String direction) {
        LoVBoard lovBoard = (LoVBoard) board;
        boolean moved = false;
        
        switch (direction) {
            case "up":    moved = lovBoard.moveHeroUp(hero); break;
            case "down":  moved = lovBoard.moveHeroDown(hero); break;
            case "left":  moved = lovBoard.moveHeroLeft(hero); break;
            case "right": moved = lovBoard.moveHeroRight(hero); break;
        }
        
        if (moved) {
            updateHeroPosition(hero);
        }
    }

    // Needed for heroPosition map for combat handler
    private void updateHeroPosition(Hero hero) {
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                if (board.getTile(r, c).getHeroOccupant() == hero) {
                    int laneIndex = (c <= 1) ? 0 : (c >= 3 && c <= 4) ? 1 : 2;
                    heroPositions.put(hero, new int[]{r, c, laneIndex});
                    return;
                }
            }
        }
    }

    // victory check after hero action
    public boolean checkVictory() {
        for (Hero h : heroes) {
            int[] pos = heroPositions.get(h);
            if (pos != null && pos[0] == 0) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }


    // ============================================
    // MONSTER TURN METHODS
    // ============================================

    // Monster's wrapper method that calls combat handler
    private void monstersTurn() {
        Output.print("\n" + Output.BRIGHT_RED + "===== MONSTERS' TURN =====" + Output.RESET);
        Output.sleep(1000);
        
        List<Monster> monsters = new ArrayList<>(activeMonsters);
        
        for (Monster m : monsters) {
            if (m.isDefeated()) continue;
            
            combat.monsterTakeTurn(m);
            
            int[] pos = monsterPositions.get(m);
            if (pos != null && pos[0] >= 7) {
                Output.print("\n" + Output.BRIGHT_RED + "💀 DEFEAT! Monsters breached your Nexus!" + Output.RESET);
                gameOver = true;
                return;
            }
        }
        
        Output.sleep(2000);
    }


    // Respawn hero at Nexus 
    private void endOfRound() {
        Output.print("\n" + Output.BRIGHT_YELLOW + "===== END OF ROUND " + round + " =====" + Output.RESET);
        
        for (Hero h : heroes) {
            if (h.isFainted()) {
                respawnHero(h);
            } else {
                h.regenAfterRound();
                Output.print(h.getName() + " regenerated 10% HP and MP");
            }
        }
        
        if (round % spawnInterval == 0 && round > 0) {
            Output.print("\n" + Output.BRIGHT_RED + "🔥 NEW MONSTER WAVE APPROACHING! 🔥" + Output.RESET);
            combat.spawnMonsterWave(round);
        }
        
        Input.waitForEnter();
    }

    // respawn hero method
    private void respawnHero(Hero hero) {
        hero.reviveHalf();  // existing Hero method
        
        // Reposition to nexus
        int[] pos = heroPositions.get(hero);
        if (pos == null) return;
        
        int laneIndex = pos[2];
        int[] laneCols = {0, 3, 6};
        int spawnRow = 7;
        int spawnCol = laneCols[laneIndex];
        
        board.getTile(spawnRow, spawnCol).setHeroOccupant(hero);
        heroPositions.put(hero, new int[]{spawnRow, spawnCol, laneIndex});
        
        // Narrative
        int heroNumber = hero.getHeroNumber();
        Output.narrative(hero.getName() + " (H" + heroNumber + ") respawned at Nexus (" + spawnRow + "," + spawnCol + ")");
    }


    // Handle end of game
    private void handleGameEnd() {
        Output.clearScreen();
        
        if (gameOver) {
            // Check if heroes won
            boolean heroesWon = checkVictory();

            if (heroesWon) {
                Output.print(Output.BRIGHT_GREEN + "\n🎉🎉🎉 VICTORY! 🎉🎉🎉" + Output.RESET);
                Output.print("The heroes have conquered the Monster Nexus!");
            } else {
                Output.print(Output.BRIGHT_RED + "\n💀💀💀 DEFEAT! 💀💀💀" + Output.RESET);
                Output.print("The monsters have overrun your Nexus...");
            }
        }
        
        Output.print("\n");
        Output.displayStatistics(statistics);
        Input.waitForEnter();
        Output.print("Thanks for playing Legends of Valor!");
    }

    // Add getter for combat
    public LoVCombatHandler getCombat() {
        return combat;
    }

    // Add setter for gameOver
    public void setGameOver(boolean value) {
        this.gameOver = value;
    }


    // Show inventory method
    public void showInventory(Hero hero) {
        Output.clearScreen();
        Output.displayHeroFullInfo(hero);
        Input.waitForEnter();
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
        LoVBoard lovBoard = (LoVBoard) board;
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            String lane = heroLanes.get(i);
            
            // Assign hero number based on order (1, 2, 3)
            lovBoard.assignHeroNumber(hero);
            
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
