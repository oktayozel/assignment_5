package src.IO;
import java.util.Scanner;

import src.Battle.Battle;
import src.Core.GameManager;
import src.Core.User;
import src.Market.Market;
import src.Hero.Hero;
import src.Inventory.InventoryEntry;
import src.Item.Item;
import src.Default.DefaultReader;

// handles all user input
public  class Input {
    private static Scanner scanner = new Scanner(System.in);
    private static src.Statistics.Statistics statistics = null;

    public Input() {
    }
    
    public static void setStatistics(src.Statistics.Statistics stats) {
        statistics = stats;
    }

    public static void isGameExit(String input){
        if (input.equals("Q")) {
            if (statistics != null) {
                Output.clearScreen();
                Output.displayStatistics(statistics);
                System.out.println(Output.BRIGHT_YELLOW + "Press ENTER to exit..." + Output.RESET);
                scanner.nextLine();
            }
            System.out.println("Exiting the game...");
            System.exit(0);  
        }
    }

    public static String getUsername(){
        Output.print("Enter your name: "); 
        String name = scanner.nextLine();
        isGameExit(name);
        return name;
    }

    public static String getGame(){
        Output.print("Enter the game you want to play, enter Q to quit game \n [1] MaH for Monsters and Heroes\n [2]LoV for Legends of Valor): "); 
        String game;

        while (true) {
            game = scanner.nextLine().trim().toUpperCase();
            isGameExit(game);
            if (game.equals("1") || game.equals("2")) {
                break;
            } else {
                System.out.print("Invalid game choice. Please enter '1' or '2': ");
            }
        }
        return game;
    }

    public static int getPartySize(){
        int size = 0;
        String input;
        while(true){
            System.out.print("Enter the hero count (1-3): ");
            input = scanner.nextLine().trim();
            isGameExit(input);
            try {
                size = Integer.parseInt(input);
                if (size >= 1 && size <= 3) {
                    break;
                } else {
                    System.out.println("Invalid hero count. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return size;
    }

    public static int getHeroName(int heroIndex){
        System.out.print("Select your hero "+ heroIndex);
        System.out.print("(1: Warrior, 2: Sorcerer, 3: Paladin): ");
        return readInt(1, 3);
    }

    public static boolean getInput(GameManager gm) {
        boolean running = true;
        String input;
        while(true){
            input = scanner.nextLine().trim().toUpperCase();
        if (!input.equals("W") &&
            !input.equals("A") &&
            !input.equals("S") &&
            !input.equals("D") &&
            !input.equals("I") &&
            !input.equals("C") &&
            !input.equals("M") &&
            !input.equals("H") &&
            !input.equals("Q")) {
            
            System.out.println("Invalid input. Please try again.");
            }
        else {
                isGameExit(input);
                break;
            }
        }

        if (input.equals("W")) {
            if (gm.getPartyPiece().moveUp(gm.getBoard())) {
                gm.handleTileEvent();
            }

        } else if (input.equals("A")) {
            if (gm.getPartyPiece().moveLeft(gm.getBoard())) {
                gm.handleTileEvent();
            }

        } else if (input.equals("S")) {
            if (gm.getPartyPiece().moveDown(gm.getBoard())) {
                gm.handleTileEvent();
            }

        } else if (input.equals("D")) {
            if (gm.getPartyPiece().moveRight(gm.getBoard())) {
                gm.handleTileEvent();
            }

        } else if (input.equals("I")) {
            // Show info AND open inventory 
            getPartyInfoInput(gm);

        } else if (input.equals("C")) {
            getPartyInfoInput(gm);

        } else if (input.equals("M")) {
            gm.getBoard().tryEnterMarket(gm.getPartyPiece().getRow(), gm.getPartyPiece().getCol(), gm.getUser());

        } else if (input.equals("H")) {
            Output.displayInstructions();
            waitForEnter();
        }
        else if (input.equals("Q")) {
            running = false;

        } else {
            System.out.println("Unknown command.");
        }
        return running;

    }

    public static boolean getMarketInput(Market market, User user) {
        String input = scanner.nextLine().trim().toUpperCase();
        if (!input.equals("B") && !input.equals("S") && !input.equals("I") && !input.equals("R") && !input.equals("E") && !input.equals("Q")) {
            System.out.println("Invalid input. Try B (buy), S (sell), R (repair), I (info), E (exit), Q (quit).");
            return true; 
        }
        isGameExit(input); 

        switch (input) {
            case "B":
                handleBuy(market, user);
                return true;
            case "S":
                handleSell(market, user);
                return true;
            case "R":
                handleRepair(market, user);
                return true;
            case "I":
                // In market, just show info without full inventory menu
                Output.printPartyInfo(user.getParty());
                waitForEnter();
                return true;
            case "E":
                return false; // exit market loop
            case "Q":
                return false; // already handled, safety
            default:
                return true;
        }
    }

    private static void getPartyInfoInput(GameManager gm) {
        if (gm.getUser().getParty().getHeroes().isEmpty()) {
            System.out.println("No heroes in party.");
            return;
        }

        while (true) {
            // First show party info
            Output.printPartyInfo(gm.getUser().getParty());
            
            System.out.println("\n===== INVENTORY MENU =====");
            System.out.println("Select a hero to manage:");
            for (int i = 0; i < gm.getUser().getParty().getHeroes().size(); i++) {
                Hero h = gm.getUser().getParty().getHeroes().get(i);
                System.out.println("(" + (i + 1) + ") " + h.getName() + " [Level: " + h.getLevel() + ", HP: " + h.getHP() + ", MP: " + h.getMP() + "]");
            }
            System.out.println("(E) Exit inventory");
            System.out.println("(Q) Quit game");
            System.out.print("Your choice: ");

            String input = scanner.nextLine().trim().toUpperCase();
            isGameExit(input);

            if (input.equals("E")) {
                return; // Exit inventory
            }

            try {
                int heroIdx = Integer.parseInt(input);
                if (heroIdx >= 1 && heroIdx <= gm.getUser().getParty().getHeroes().size()) {
                    Hero hero = gm.getUser().getParty().getHeroes().get(heroIdx - 1);
                    handleHeroInventory(hero);
                } else {
                    System.out.println("Invalid hero number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a hero number, E to exit, or Q to quit.");
            }
        }
    }

    private static void handleHeroInventory(Hero hero) {
        while (true) {
            System.out.println("\n===== " + hero.getName() + "'s Inventory =====");
            System.out.println("(1) Equip Weapon");
            System.out.println("(2) Equip Armor");
            System.out.println("(3) Use Potion");
            System.out.println("(4) View Full Info");
            System.out.println("(E) Back to hero selection");
            System.out.println("(Q) Quit game");
            System.out.print("Your choice: ");

            String input = scanner.nextLine().trim().toUpperCase();
            isGameExit(input);

            if (input.equals("E")) {
                return; // Back to hero selection
            }

            switch (input) {
                case "1":
                    equipWeapon(hero);
                    break;
                case "2":
                    equipArmor(hero);
                    break;
                case "3":
                    usePotion(hero);
                    break;
                case "4":
                    Output.displayHeroFullInfo(hero);
                    waitForEnter();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void equipWeapon(Hero hero) {
        java.util.List<src.Item.Weapon> weapons = hero.getAvailableWeapons();
        
        if (weapons.isEmpty()) {
            System.out.println("No weapons in inventory.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
            return;
        }

        System.out.println("\n--- Available Weapons ---");
        for (int i = 0; i < weapons.size(); i++) {
            src.Item.Weapon w = weapons.get(i);
            String equipped = (hero.getEquippedWeapon() != null && hero.getEquippedWeapon().getName().equals(w.getName())) ? " [EQUIPPED]" : "";
            System.out.println("(" + (i + 1) + ") " + w.getName() + " - Damage: " + w.getDamage() + ", Hands: " + w.getHands() + equipped);
        }
        System.out.println("(0) Cancel");
        System.out.print("Select weapon to equip: ");

        int choice = readInt(0, weapons.size());
        if (choice == 0) {
            return;
        }

        if (hero.equipWeaponByIndex(choice - 1)) {
            System.out.println(hero.getName() + " equipped " + weapons.get(choice - 1).getName() + "!");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }

    private static void equipArmor(Hero hero) {
        java.util.List<src.Item.Armor> armors = hero.getAvailableArmor();
        
        if (armors.isEmpty()) {
            System.out.println("No armor in inventory.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
            return;
        }

        System.out.println("\n--- Available Armor ---");
        for (int i = 0; i < armors.size(); i++) {
            src.Item.Armor a = armors.get(i);
            String equipped = (hero.getEquippedArmor() != null && hero.getEquippedArmor().getName().equals(a.getName())) ? " [EQUIPPED]" : "";
            System.out.println("(" + (i + 1) + ") " + a.getName() + " - Reduction: " + a.getDamageReduction() + equipped);
        }
        System.out.println("(0) Cancel");
        System.out.print("Select armor to equip: ");

        int choice = readInt(0, armors.size());
        if (choice == 0) {
            return;
        }

        if (hero.equipArmorByIndex(choice - 1)) {
            System.out.println(hero.getName() + " equipped " + armors.get(choice - 1).getName() + "!");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }

    private static void usePotion(Hero hero) {
        java.util.List<src.Item.Potion> potions = hero.getAvailablePotions();
        
        if (potions.isEmpty()) {
            System.out.println("No potions in inventory.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
            return;
        }

        System.out.println("\n--- Available Potions ---");
        for (int i = 0; i < potions.size(); i++) {
            src.Item.Potion p = potions.get(i);
            InventoryEntry entry = hero.getInventory().getEntryByName(p.getName());
            int quantity = (entry != null) ? entry.getQuantity() : 0;
            System.out.println("(" + (i + 1) + ") " + p.getName() + " - " + p.getEffectType() + " +" + p.getEffectAmount() + " (x" + quantity + ")");
        }
        System.out.println("(0) Cancel");
        System.out.print("Select potion to use: ");

        int choice = readInt(0, potions.size());
        if (choice == 0) {
            return;
        }

        if (hero.usePotionByIndex(choice - 1)) {
            System.out.println(hero.getName() + " used " + potions.get(choice - 1).getName() + "!");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }

    private static void handleBuy(Market market, User user) {
        if (user.getParty().getHeroes().isEmpty()) {
            System.out.println("No heroes in party to buy items.");
            return;
        }


        // Choose hero
        System.out.println("Select hero to buy for:");
        for (int i = 0; i < user.getParty().getHeroes().size(); i++) {
            Hero h = user.getParty().getHeroes().get(i);
            System.out.println("(" + (i + 1) + ") " + h.getName() + " [Level: " + h.getLevel() + ", Gold: " + h.getGold() + "]");
        }
        System.out.print("Hero number: ");
        
        int heroIdx = readInt(1, user.getParty().getHeroes().size());


        Hero hero = user.getParty().getHeroes().get(heroIdx - 1);
        System.out.println("Hero Gold Budget: " + hero.getGold());

        // Choose item to buy index
        int primaryCount = market.getItems().size();
        int secondCount = market.getSecondHandItems().size();
        if (primaryCount + secondCount == 0) {
            System.out.println("Market is empty.");
            return;
        }
        System.out.print("Enter item index to buy: ");
        int itemIdx = readInt(1, primaryCount + secondCount);


        Item selected;
        if (itemIdx <= primaryCount) {
            selected = market.getItems().get(itemIdx - 1);
        } else {
            selected = market.getSecondHandItems().get(itemIdx - primaryCount - 1);
        }
        if (market.buyItem(hero, selected)) {
            System.out.println("Purchase successful.");
        } else {
            System.out.println("Purchase failed.");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));

    }

    private static void handleSell(Market market, User user) {
        if (user.getParty().getHeroes().isEmpty()) {
            System.out.println("No heroes in party to sell items.");
            return;
        }
        System.out.println("Select hero to sell from:");
        for (int i = 0; i < user.getParty().getHeroes().size(); i++) {
            Hero h = user.getParty().getHeroes().get(i);
            System.out.println("(" + (i + 1) + ") " + h.getName() + " [Gold: " + h.getGold() + "]");
        }
        System.out.print("Hero number: ");
        int heroIdx = readInt(1, user.getParty().getHeroes().size());
        Hero hero = user.getParty().getHeroes().get(heroIdx - 1);
        if (hero.getInventory().getEntries().isEmpty()) {
            System.out.println("Inventory empty.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
            return;
        }
        System.out.println("Select item to sell:");
        for (int i = 0; i < hero.getInventory().getEntries().size(); i++) {
            InventoryEntry entry = hero.getInventory().getEntries().get(i);
            System.out.println("(" + (i + 1) + ") " + entry.getItem().getName() + " x" + entry.getQuantity() + " (Price: " + entry.getItem().getPrice() + ")");
        }
        System.out.print("Item number: ");
        int itemIdx = readInt(1, hero.getInventory().getEntries().size());
        InventoryEntry entry = hero.getInventory().getEntries().get(itemIdx - 1);

        if (market.sellItem(hero, entry.getItem().getName())) {
            System.out.println("Sale successful.");
        } else {
            System.out.println("Sale failed.");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }
    
    private static void handleRepair(Market market, User user) {
        if (user.getParty().getHeroes().isEmpty()) {
            System.out.println("No heroes in party to repair items.");
            return;
        }
        System.out.println("Select hero to repair items for:");
        for (int i = 0; i < user.getParty().getHeroes().size(); i++) {
            Hero h = user.getParty().getHeroes().get(i);
            System.out.println("(" + (i + 1) + ") " + h.getName() + " [Gold: " + h.getGold() + "]");
        }
        System.out.print("Hero number: ");
        int heroIdx = readInt(1, user.getParty().getHeroes().size());
        Hero hero = user.getParty().getHeroes().get(heroIdx - 1);
        
        // Find broken items using Repairable interface
        java.util.List<Item> brokenItems = new java.util.ArrayList<>();
        for (InventoryEntry entry : hero.getInventory().getEntries()) {
            Item item = entry.getItem();
            if (item instanceof src.Interface.Repairable) {
                src.Interface.Repairable repairable = (src.Interface.Repairable) item;
                if (repairable.isBroken()) {
                    brokenItems.add(item);
                }
            }
        }
        
        if (brokenItems.isEmpty()) {
            System.out.println("No broken items to repair.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
            return;
        }
        
        System.out.println("Select broken item to repair:");
        for (int i = 0; i < brokenItems.size(); i++) {
            Item item = brokenItems.get(i);
            src.Interface.Repairable repairable = (src.Interface.Repairable) item;
            int repairCost = repairable.getRepairCost();
            System.out.println("(" + (i + 1) + ") " + item.getName() + " (Repair cost: " + repairCost + " gold)");
        }
        System.out.print("Item number: ");
        int itemIdx = readInt(1, brokenItems.size());
        Item itemToRepair = brokenItems.get(itemIdx - 1);

        if (market.repairItem(hero, itemToRepair)) {
            System.out.println("Repair successful.");
        } else {
            System.out.println("Repair failed.");
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }

    public static int readInt(int min, int max) {
        while (true) {
            String raw = scanner.nextLine().trim().toUpperCase();
            isGameExit(raw);
            try {
                int val = Integer.parseInt(raw);
                if (val < min || val > max) {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter a number between " + min + " and " + max + ": ");
            }
        }
    }

    
    public static boolean getBattleInput(GameManager gm, Battle battle) {
        // Deprecated with per-hero turn system. Keep minimal support for 'I' and 'Q' to avoid breaking older flows.
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("I")) {
            battle.printInfo();
            waitForEnter();
            return true;
        }
        // Q will exit the game as per global convention
        isGameExit(input);
        // Any other input is ignored in this deprecated path
        return true;
    }

    // Per-hero action prompt for the multi-hero turn system
    // Returns one of: A, S, P, E. If user presses I, shows info and re-prompts. Q exits the game via isGameExit.
    public static char getHeroBattleAction(Hero hero, Battle battle) {
        while (true) {
            System.out.print("Action for " + hero.getName() + " (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): ");
            String raw = scanner.nextLine().trim().toUpperCase();
            isGameExit(raw); // Q exits entire game consistently with other inputs
            if (!raw.equals("A") && !raw.equals("S") && !raw.equals("P") && !raw.equals("E") && !raw.equals("I") && !raw.equals("Q")) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            if (raw.equals("I")) {
                System.out.println("\n--- Battle Info ---");
                battle.printInfo();
                waitForEnter();
                continue; // re-prompt for this hero's action
            }
            return raw.charAt(0);
        }
    }

    public static void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        try {
            scanner.nextLine();
        } catch (Exception ignored) {}
    }

    public static void inputNewGame(src.Statistics.Statistics statistics) {
        System.out.print("Do you want to start a new game? (Y/N), to exit press Q: ");
        while (true) {
            String raw = scanner.nextLine().trim().toUpperCase();
            isGameExit(raw);
            if (raw.equals("Y")) {
                System.out.println("Starting a new game...");
                Output.sleep(2000);
                GameManager gm = new GameManager(statistics);
                gm.start();
                break;
            } else if (raw.equals("N")) {
                Output.clearScreen();
                Output.displayStatistics(statistics);
                System.out.println(Output.BRIGHT_YELLOW + "Press ENTER to exit..." + Output.RESET);
                scanner.nextLine();
                System.out.println("Exiting the game...");
                System.exit(0);
            } else {
                System.out.print("Invalid input. Please enter Y (yes) or N (no), or Q to quit: ");
            }
        }

    }









}
