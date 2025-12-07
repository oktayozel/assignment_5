package src.Utils.IO;

import src.Monster.Monster;
import src.Utils.Default.DefaultReader;
import src.Utils.Statistics.Statistics;
import src.Hero.Hero;
import src.Item.Item;
import src.Item.Weapon;
import src.Item.Armor;
import src.Inventory.InventoryEntry;
import src.Market.Market;
import src.Battle.Battle;
import src.Core.User;
import src.Core.Party;
import java.util.Arrays;
import java.util.List;

// handles all display output
public class Output {
    
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_CYAN = "\u001B[96m";



    static List<String> paladingArt = Arrays.asList(
    "                       ,` -.)",
    "                      ( _/-\\-._",
    "                     /,|`--._,-^|            ,",
    "                     \\_| |`-._/||          ,'|",
    "                       |  `-, / |         /  /",
    "                       |     || |        /  /",
    "                        `r-._||/   __   /  /",
    "                    __,-<_     )`-/  `./  /",
    "                   '  \\   `---'   \\   /  /",
    "                       |           |./  /",
    "                       /           //  /",
    "                   \\_/' \\         |/  /",
    "                    |    |   _,^-'/  /",
    "                    |    , ``  (\\/  /_",
    "                     \\,.->._    \\\\X-=/^",
    "                     (  /   `-._//^`",
    "                      `Y-.____(__}",
    "                       |     {__)",
    "                             ("
    );



    static List<String> sorcererArt = Arrays.asList(
    "                               ____ ",
    "                                .'* *.'",
    "                             __/_*_*(_",
    "                            / _______ \\",
    "                           _\\_)/___\\(_/_ ",
    "                          / _((\\- -/))_ \\",
    "                          \\ \\())(-)(()/ /",
    "                           ' \\(((()))/ '",
    "                          / ' \\)).))/ ' \\",
    "                         / _ \\ - | - /_  \\",
    "                        (   ( .;''';. .'  )",
    "                        _\"__ /    )\\ __\"/_",
    "                          \\/  \\   ' /  \\/",
    "                           .'  '...' ' )",
    "                            / /  |  \\ \\",
    "                           / .   .   . \\",
    "                          /   .     .   \\",
    "                         /   /   |   \\   \\",
    "                       .'   /    b    '.  '",
    "                   _.-'    /     Bb     '-. '",
    "                          |      BBb       '-. ",
    "                   _______\\____.dBBBb.________)");


    static List<String> warriorArt = Arrays.asList(
    "                             {}",
    "                            .--.",
    "                           /.--.\\",
    "                           |====|",
    "                           |`::`|",
    "                       .-;`\\..../`;_.-^-._",
    "                      /  |...::..|`   :   `|",
    "                     |   /'''::''|   .:.   |",
    "                     ;--'\\   ::  |..:::::..|",
    "                     <__> >._::_.| ':::::' |",
    "                     |  |/   ^^  |   ':'   |",
    "                     \\::/|       \\    :    /",
    "                     |||\\|        \\   :   /",
    "                     ''' |___/\\___|`-.:.-`",
    "                          \\_ || _/    `",
    "                          <_ >< _>",
    "                          |  ||  |",
    "                          |  ||  |",
    "                         _\\.:||:./_",
    "                        /____/\\____\\");


    public Output() {
    }


    public static void someSpace(){
        int cnt = 15;
        while(cnt > 0){
            System.out.println();
            cnt--;
        }
    }
    public static void clearScreen(){
        int cnt = 100;
        while(cnt > 0){
            System.out.println();
            cnt--;
        }
    }

    public static void boardBanner(String game){
        if(game.equals("mah")){
            System.out.println(BRIGHT_CYAN + "██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗" + RESET + "       " + BRIGHT_RED + "███╗   ███╗" + RESET + "      " + BRIGHT_YELLOW + "██╗  ██╗" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗" + RESET + "    " + BRIGHT_RED + "████╗ ████║" + RESET + "      " + BRIGHT_YELLOW + "██║  ██║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝" + RESET + "    " + BRIGHT_RED + "██╔████╔██║" + RESET + "      " + BRIGHT_YELLOW + "███████║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗" + RESET + "    " + BRIGHT_RED + "██║╚██╔╝██║" + RESET + " and  " + BRIGHT_YELLOW + "██╔══██║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝" + RESET + "    " + BRIGHT_RED + "██║ ╚═╝ ██║" + RESET + "      " + BRIGHT_YELLOW + "██║  ██║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝" + RESET + "       " + BRIGHT_RED + "╚═╝     ╚═╝" + RESET + "      " + BRIGHT_YELLOW + "╚═╝  ╚═╝" + RESET + "        ");
        }
        else if (game.equals("lov")){
            System.out.println(BRIGHT_CYAN + "██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗" + RESET + "       " + BRIGHT_YELLOW + "  ██████╗" + RESET + "      " + BRIGHT_YELLOW +  "██╗   ██╗" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗" + RESET + "    " + BRIGHT_YELLOW + " ██╔═══██╗" + RESET + "     " + BRIGHT_YELLOW +  "██║   ██║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝" + RESET + "    " + BRIGHT_YELLOW + " ██║   ██║" + RESET + "     " + BRIGHT_YELLOW +  "██║   ██║" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗" + RESET + "    " + BRIGHT_YELLOW + " ██║   ██║" + RESET + " and  " + BRIGHT_YELLOW + "╚██╗ ██╔╝" + RESET + "        ");
            System.out.println(BRIGHT_CYAN + "███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝" + RESET + "    " + BRIGHT_YELLOW + " ╚██████╔╝" + RESET + "      " + BRIGHT_YELLOW + " ╚████╔╝ " + RESET + "        ");
        }
        someSpace();

    }
    public static void gameInitializationMessage(String game) {
        clearScreen();
        if (game.equals("mah")){
            System.out.println(BRIGHT_CYAN + "██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗                    " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗                 " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝                 " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗                 " + RESET);
            System.out.println(BRIGHT_CYAN + "███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝                 " + RESET);
            System.out.println(BRIGHT_CYAN + "╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝                    " + RESET);
            System.out.println("                                                                               ");
            System.out.println(BRIGHT_RED + "███╗   ███╗ ██████╗ ███╗   ██╗███████╗████████╗███████╗██████╗ ███████╗        " + RESET);
            System.out.println(BRIGHT_RED + "████╗ ████║██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝        " + RESET);
            System.out.println(BRIGHT_RED + "██╔████╔██║██║   ██║██╔██╗ ██║███████╗   ██║   █████╗  ██████╔╝███████╗        " + RESET);
            System.out.println(BRIGHT_RED + "██║╚██╔╝██║██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══╝  ██╔══██╗╚════██║        " + RESET);
            System.out.println(BRIGHT_RED + "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║███████║   ██║   ███████╗██║  ██║███████║        " + RESET);
            System.out.println(BRIGHT_RED + "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝        " + RESET);
            System.out.println("                                                                               ");
            System.out.println(BRIGHT_YELLOW + " █████╗ ███╗   ██╗██████╗     ██╗  ██╗███████╗██████╗  ██████╗ ███████╗███████╗" + RESET);
            System.out.println(BRIGHT_YELLOW + "██╔══██╗████╗  ██║██╔══██╗    ██║  ██║██╔════╝██╔══██╗██╔═══██╗██╔════╝██╔════╝" + RESET);
            System.out.println(BRIGHT_YELLOW + "███████║██╔██╗ ██║██║  ██║    ███████║█████╗  ██████╔╝██║   ██║█████╗  ███████╗" + RESET);
            System.out.println(BRIGHT_YELLOW + "██╔══██║██║╚██╗██║██║  ██║    ██╔══██║██╔══╝  ██╔══██╗██║   ██║██╔══╝  ╚════██║" + RESET);
            System.out.println(BRIGHT_YELLOW + "██║  ██║██║ ╚████║██████╔╝    ██║  ██║███████╗██║  ██║╚██████╔╝███████╗███████║" + RESET);
            System.out.println(BRIGHT_YELLOW + "╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝" + RESET);
        }
        else if ( game.equals("lov")){
            System.out.println(BRIGHT_CYAN + "██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗                    " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗                 " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝                 " + RESET);
            System.out.println(BRIGHT_CYAN + "██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗                 " + RESET);
            System.out.println(BRIGHT_CYAN + "███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝                 " + RESET);
            System.out.println(BRIGHT_CYAN + "╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝                    " + RESET);
            System.out.println("                                                                               ");
            System.out.println(BRIGHT_YELLOW + "  ██████╗ ███████╗   ██╗   ██╗ █████╗ ██╗      ██████╗ ██████╗  "+ RESET);
            System.out.println(BRIGHT_YELLOW + " ██╔═══██╗██╔════╝   ██║   ██║██╔══██╗██║     ██╔═══██╗██╔══██╗ "+ RESET);
            System.out.println(BRIGHT_YELLOW + " ██║   ██║█████╗     ██║   ██║███████║██║     ██║   ██║██████╔╝ "+ RESET); 
            System.out.println(BRIGHT_YELLOW + " ██║   ██║██╔══╝     ╚██╗ ██╔╝██╔══██║██║     ██║   ██║██╔══██╗ "+ RESET); 
            System.out.println(BRIGHT_YELLOW + " ╚██████╔╝██║         ╚████╔╝ ██║  ██║███████╗╚██████╔╝██║  ██║ "+ RESET);
            System.out.println(BRIGHT_YELLOW + "  ╚═════╝ ╚═╝          ╚═══╝  ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝ "+ RESET);
        }
    }
    public static void print(String message){
        System.out.print(message);
    }




    public static void animateString(List<String> animationFrames) {
        for (String frame : animationFrames) {
            System.out.print("\n" + frame);
            try {
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    public static void displayMonsterStatistics(Monster monster) {
        System.out.println(BRIGHT_RED + "===== STATISTICS of " + monster.getName() + " =====" + RESET);
        System.out.println("Level: " + YELLOW + monster.getLevel() + RESET);
        System.out.println("Health Points (HP): " + RED + monster.getHP() + RESET);
        System.out.println("Base Damage: " + BRIGHT_RED + monster.getBaseDamage() + RESET);
        System.out.println("Defense: " + CYAN + monster.getDefense() + RESET);
        System.out.println("Dodge Ability: " + MAGENTA + monster.getDodge() + RESET);
        System.out.println(BRIGHT_RED + "=====================================" + RESET);
        
    }

    public static void printPartyInfo(Party party) {
        clearScreen();
        System.out.println(BRIGHT_GREEN + "===============================================================================================");
        System.out.println("                         ===      ==      ==  ========  =========             ");
        System.out.println("                          =       == ==   ==  ==        ==     ==   ");
        System.out.println("                          =       ==  ==  ==  ======    ==     ==         ");
        System.out.println("                          =       ==   == ==  ==        ==     ==  ");
        System.out.println("                         ===      ==      ==  ==        =========                ");
        System.out.println("===============================================================================================" + RESET);
        List<Hero> heroes = party.getHeroes();
        for (Hero h : heroes) {
            Output.displayHeroFullInfo(h);
        }
    }
    public static void displayHeroFullInfo(Hero hero) {


        System.out.println(BRIGHT_GREEN + "===== HERO: " + hero.getName() + " =====" + RESET);
        System.out.println("Class: " + CYAN + hero.getHeroClass() + RESET);
        System.out.println("Level: " + YELLOW + hero.getLevel() + RESET);
        System.out.println("Health Points (HP): " + GREEN + hero.getHP() + RESET);
        System.out.println("Mana Points (MP): " + BLUE + hero.getMP() + RESET);
        System.out.println("Strength: " + RED + hero.getStrength() + RESET);
        System.out.println("Agility: " + MAGENTA + hero.getAgility() + RESET);
        System.out.println("Dexterity: " + CYAN + hero.getDexterity() + RESET);
        System.out.println("Experience: " + BRIGHT_YELLOW + hero.getExperience() + RESET);
        System.out.println("Gold: " + YELLOW + hero.getGold() + RESET);

        Weapon w = hero.getEquippedWeapon();
        Armor a = hero.getEquippedArmor();
        if (w != null) {
            String handInfo = w.getHands() == 2 ? " (2H)" : (hero.isUsingTwoHands() ? " (1H, using 2H)" : " (1H)");
            System.out.println("Equipped Weapon: " + BRIGHT_CYAN + w.getName() + handInfo + " (DMG:" + w.getDamage() + ")" + RESET);
        } else {
            System.out.println("Equipped Weapon: " + RED + "None" + RESET);
        }
        System.out.println("Equipped Armor:  " + (a != null ? BRIGHT_CYAN + a.getName() + " (DR:" + a.getDamageReduction() + ")" + RESET : RED + "None" + RESET));

        System.out.println("Inventory:");
        if (hero.getInventory() == null || hero.getInventory().getEntries().isEmpty()) {
            System.out.println("  " + YELLOW + "(empty)" + RESET);
        } else {
            for (InventoryEntry e : hero.getInventory().getEntries()) {
                System.out.println("  - " + CYAN + e.getItem().getName() + RESET + " x" + e.getQuantity());
            }
        }
        System.out.println(BRIGHT_GREEN + "=====================================" + RESET);
    }

    public static void displayInstructions() {
        clearScreen();
        System.out.println(BRIGHT_CYAN + "\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          LEGENDS: MONSTERS AND HEROES - HELP               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println(BRIGHT_YELLOW + "\n=== OBJECTIVE ===" + RESET);
        System.out.println("Battle monsters, gain experience, level up, and survive!");
        
        System.out.println(BRIGHT_YELLOW + "\n=== WORLD CONTROLS ===" + RESET);
        System.out.println("  " + GREEN + "W/w" + RESET + " - Move Up");
        System.out.println("  " + GREEN + "A/a" + RESET + " - Move Left");
        System.out.println("  " + GREEN + "S/s" + RESET + " - Move Down");
        System.out.println("  " + GREEN + "D/d" + RESET + " - Move Right");
        System.out.println("  " + CYAN + "I/i" + RESET + " - View party info & manage inventory");
        System.out.println("  " + CYAN + "C/c" + RESET + " - Character/inventory menu");
        System.out.println("  " + YELLOW + "M/m" + RESET + " - Enter market (only on market tiles)");
        System.out.println("  " + BLUE + "H/h" + RESET + " - Display this help");
        System.out.println("  " + RED + "Q/q" + RESET + " - Quit game");
        
        System.out.println(BRIGHT_YELLOW + "\n=== TILE TYPES ===" + RESET);
        System.out.println("  " + BRIGHT_GREEN + "P" + RESET + " - Your party");
        System.out.println("  " + YELLOW + "M" + RESET + " - Market (buy/sell items)");
        System.out.println("  " + RED + "X" + RESET + " - Inaccessible");
        System.out.println("  " + CYAN + "(space)" + RESET + " - Common (random battle chance)");
        
        System.out.println(BRIGHT_YELLOW + "\n=== BATTLE ACTIONS ===" + RESET);
        System.out.println("  " + RED + "A" + RESET + " - Attack with equipped weapon");
        System.out.println("  " + MAGENTA + "S" + RESET + " - Cast spell (consumes MP and spell)");
        System.out.println("  " + BLUE + "P" + RESET + " - Use potion (HP/MP/stat boost)");
        System.out.println("  " + CYAN + "E" + RESET + " - Equip weapon or armor");
        System.out.println("  " + YELLOW + "I" + RESET + " - View battle info");
        
        System.out.println(BRIGHT_YELLOW + "\n=== HERO CLASSES ===" + RESET);
        System.out.println("  " + BRIGHT_RED + "Warrior" + RESET + "  - Favors Strength & Agility");
        System.out.println("  " + BRIGHT_CYAN + "Sorcerer" + RESET + " - Favors Dexterity & Agility");
        System.out.println("  " + BRIGHT_YELLOW + "Paladin" + RESET + "  - Favors Strength & Dexterity");
        
        System.out.println(BRIGHT_YELLOW + "\n=== LEVEL UP ===" + RESET);
        System.out.println("  - Requires " + BRIGHT_CYAN + "EXP = current_level × 10" + RESET);
        System.out.println("  - All stats increase by " + GREEN + "5%" + RESET);
        System.out.println("  - Favored stats increase by additional " + BRIGHT_GREEN + "5%" + RESET);
        System.out.println("  - HP resets to " + GREEN + "level × 100" + RESET);
        System.out.println("  - MP increases by " + BLUE + "10%" + RESET);
        
        System.out.println(BRIGHT_YELLOW + "\n=== TIPS ===" + RESET);
        System.out.println("  - " + YELLOW + "Buy equipment at markets before battles" + RESET);
        System.out.println("  - " + MAGENTA + "Use spells for damage + debuffs on monsters" + RESET);
        System.out.println("  - " + CYAN + "Manage inventory (I/C) to equip items outside battle" + RESET);
        System.out.println("  - " + GREEN + "Heroes regenerate 10% HP/MP each battle round" + RESET);
        System.out.println("  - " + BRIGHT_GREEN + "Fainted heroes revive with half HP/MP after victory" + RESET);
    }

    // Battle-focused hero info (equipped items)
    public static void displayHeroBattleInfo(Hero hero) {
        System.out.println(BRIGHT_GREEN + "===== HERO in Battle: " + hero.getName() + " =====" + RESET);
        System.out.println("Level: " + YELLOW + hero.getLevel() + RESET);
        System.out.println("HP: " + GREEN + hero.getHP() + RESET);
        System.out.println("MP: " + BLUE + hero.getMP() + RESET);
        Weapon w = hero.getEquippedWeapon();
        Armor a = hero.getEquippedArmor();
        if (w != null) {
            String handInfo = w.getHands() == 2 ? " (2H)" : (hero.isUsingTwoHands() ? " (1H, using 2H)" : " (1H)");
            System.out.println("Equipped Weapon: " + BRIGHT_CYAN + w.getName() + handInfo + " (DMG:" + w.getDamage() + ")" + RESET);
        } else {
            System.out.println("Equipped Weapon: " + RED + "None" + RESET);
        }
        System.out.println("Equipped Armor: " + (a != null ? BRIGHT_CYAN + a.getName() + " (DR:" + a.getDamageReduction() + ")" + RESET : RED + "None" + RESET));
        System.out.println(BRIGHT_GREEN + "=====================================" + RESET);
    }

    public static void displayMarket(Market market) {
        clearScreen();
        System.out.println(YELLOW + "+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+");
        System.out.println("|M|a|r|k|e|t|         |M|a|r|k|e|t|        |M|a|r|k|e|t|        |M|a|r|k|e|t|       |M|a|r|k|e|t|");
        System.out.println("+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+" + RESET);
      


        System.out.println(BRIGHT_YELLOW + "=====================================================================================================");
        System.out.println("Welcome to the Market! Here you can buy and sell items to enhance your heroes.");
        System.out.println("=================================================================================================" + RESET);
        int idx = 1;
        System.out.println(CYAN + "-- Primary Stock --" + RESET);
        for (Item item : market.getItems()) {
            System.out.println("[" + YELLOW + idx + RESET + "] " + item);
            idx++;
        }
        if (!market.getSecondHandItems().isEmpty()) {
            System.out.println(MAGENTA + "-- Second-Hand Stock --" + RESET);
            for (Item item : market.getSecondHandItems()) {
                System.out.println("[" + YELLOW + idx + RESET + "] " + item + " " + MAGENTA + "(resale)" + RESET);
                idx++;
            }
        }
        System.out.println(BRIGHT_CYAN + "Total items listed: " + (idx - 1) + RESET);
        someSpace();

    }

    public static void displayBattle(Battle battle){
        clearScreen();
        System.out.println(BRIGHT_RED + "=====================================================================================================================================================");
        System.out.println(" ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄      ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄ ");             
        System.out.println("▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌");             
        System.out.println("▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌");             
        System.out.println("▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌");             
        System.out.println("▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌");             
        System.out.println("▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌");             
        System.out.println("▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌");             
        System.out.println("▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌");             
        System.out.println("▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌       ▐░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌▐░▌       ▐░▌");             
        System.out.println("▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌    ▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░▌       ▐░▌");             
        System.out.println(" ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀      ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀         ▀ ");  
        System.out.println("=====================================================================================================================================================" + RESET);
        someSpace();
        System.out.println(BRIGHT_GREEN + "HEROES" + RESET + "                                                                " + YELLOW + "Round {" + battle.getRound() + "}" + RESET + "                                                                   " + BRIGHT_RED + "MONSTERS" + RESET);
        System.out.println("=====================================================================================================================================================");
        for( int i = 0 ; i < battle.getParty().getHeroes().size(); i++){
            Hero hero = battle.getParty().getHeroes().get(i);
            Monster monster = battle.getMonsters().get(i);
            String heroStr = GREEN + hero.getName() + RESET + " (HP: " + BRIGHT_GREEN + hero.getHP() + RESET + ", MP: " + BLUE + hero.getMP() + RESET + ")";
            String monsterStr = RED + monster.getName() + RESET + " (HP: " + BRIGHT_RED + monster.getHP() + RESET + ")";
            System.out.printf("%-100s                              " + YELLOW + "X" + RESET + "                                           %-100s%n", heroStr, monsterStr);
        }

    }


    public static void displaySecondWelcomeMessage(User user){
        clearScreen();
        System.out.println(BRIGHT_CYAN + "Welcome to Legends: Monsters and Heroes, " + BRIGHT_YELLOW + user.getName() + BRIGHT_CYAN + "!" + RESET);
        System.out.println(BRIGHT_GREEN + "Prepare yourself for new adventures and challenges ahead!" + RESET);
        sleep(500);
        System.out.println(CYAN + "Your game will start shotly with following heroes" + RESET);
        for( int i = 0 ; i < user.getParty().getHeroes().size(); i++){
            sleep(500);
            Hero hero = user.getParty().getHeroes().get(i);
            if(hero.getHeroClass().equals("Paladin")) {
                animateString(paladingArt);
            } else if (hero.getHeroClass().equals("Sorcerer")) {
                animateString(sorcererArt);
            } else if (hero.getHeroClass().equals("Warrior")) {
                animateString(warriorArt);
            }
            
            System.out.println("\n\n              " + BRIGHT_GREEN + "Hero " + (i + 1) + ": " + BRIGHT_YELLOW + hero.getName() + RESET);
            System.out.println("\n\n\n\n\n\n");
        }
        System.out.println(BRIGHT_RED + "I beg mercy to you and the heroes, cause monsters won't show any!" + RESET);
        sleep(5000);
    }

    public static void displaySecondWelcomeMessage(User user1, User user2 ){
        clearScreen();
        System.out.println(BRIGHT_CYAN + "Welcome to Legends: Monsters and Heroes, " + BRIGHT_YELLOW + user1.getName() + BRIGHT_CYAN + " and " + BRIGHT_YELLOW + user2.getName() + BRIGHT_CYAN + "!" + RESET);
        System.out.println(BRIGHT_GREEN + "Prepare yourselves for new adventures and challenges ahead!" + RESET);
        sleep(500);
        System.out.println(CYAN + "Your game will start shotly with following heroes" + RESET);
//        for( int i = 0 ; i < user1.getParty().getHeroes().size(); i++){
//            sleep(500);
//            Hero hero = user1.getParty().getHeroes().get(i);
//            if(hero.getHeroClass().equals("Paladin")) {
//                animateString(paladingArt);
//            } else if (hero.getHeroClass().equals("Sorcerer")) {
//                animateString(sorcererArt);
//            } else if (hero.getHeroClass().equals("Warrior")) {
//                animateString(warriorArt);
//            }
//            
//            System.out.println("\n\n              " + BRIGHT_GREEN + "Hero " + (i + 1) + ": " + BRIGHT_YELLOW + hero.getName() + RESET);
//            System.out.println("\n\n\n\n\n\n");
//        }
//        for( int i = 0 ; i < user2.getParty().getHeroes().size(); i++){
//            sleep(500);
//            Hero hero = user2.getParty().getHeroes().get(i);
//            if(hero.getHeroClass().equals("Paladin")) {
//                animateString(paladingArt);
//            } else if (hero.getHeroClass().equals("Sorcerer")) {
//                animateString(sorcererArt);
//            } else if (hero.getHeroClass().equals("Warrior")) {
//                animateString(warriorArt);
//            }
//            
//            System.out.println("\n\n              " + BRIGHT_GREEN + "Hero " + (i + 1) + ": " + BRIGHT_YELLOW + hero.getName() + RESET);
//            System.out.println("\n\n\n\n\n\n");
//        }
        System.out.println(BRIGHT_RED + "I beg mercy to you and the heroes, cause monsters won't show any!" + RESET);
        sleep(5000);

    }





    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void printRandomNoBattleMessage(int number){

        if(number < 10  && number > 0 ){
            narrative("You sneak past the monsters without being noticed. Lucky you!", BRIGHT_GREEN);
            return;
        }
        else if(number <20 && number >=10 ){
            narrative("The monsters are too busy fighting among themselves to notice you. You slip by unnoticed.", GREEN);
            return;
        }
        else if(number <30 && number >=20 ){
            narrative("A sudden fog envelops the area, allowing you to move unseen past the monsters.", CYAN);
            return;
        }
        else if(number <40 && number >=30 ){    
            narrative("No monsters in sight! You continue your journey unchallenged.", BRIGHT_GREEN);
            return;
        }
        if(number <50 && number >=40 ){    
            narrative("No monsters here! Cowards", YELLOW);
            return;
        }
        else if(number <60 && number >=50 ){    
            narrative("The monsters seem to have retreated for now. You proceed with caution.", GREEN);
            return;
        }
        else if(number <70 && number >=60 ){
            narrative("You will keep chasing them until no monster is left!", BRIGHT_YELLOW);
            return;
        }
        else{
            narrative("Looks like you are very good at your job, no monsters here!", BRIGHT_GREEN);
            return;
        }

    }

    // Narrative with color (overloaded method)
    public static void narrative(String message, String color){
        int cnt = 10;
        while (cnt > 0) {
            System.out.println(">");
            sleep(200);
            cnt--;
        }
        System.out.println("\n>--- " + color + message + RESET + " ---\n");
        while (cnt > 0) {
            System.out.println(">");
            cnt--;
        }
        sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }
    
    // Default narrative method (backwards compatibility)
    public static void narrative(String message){
        narrative(message, ""); // No color by default
    }


    // Display statistics
    public static void displayStatistics(Statistics stats) {
        String BRIGHT_CYAN = "\u001B[96m";
        String BRIGHT_GREEN = "\u001B[92m";
        String BRIGHT_YELLOW = "\u001B[93m";
        String RESET = "\u001B[0m";
        
        String separator = "==================================================";
        System.out.println(BRIGHT_CYAN + "\n" + separator);
        System.out.println("          GAME STATISTICS");
        System.out.println(separator + RESET);
        System.out.println("Total Games Played:       " + BRIGHT_GREEN + stats.getTotalGamesPlayed() + RESET);
        System.out.println("Total Battles Won:        " + BRIGHT_GREEN + stats.getTotalBattlesWon() + RESET);
        System.out.println("Total Heroes Levelled Up: " + BRIGHT_YELLOW + stats.getTotalHeroesLevelledUp() + RESET);
        System.out.println("Total Monsters Defeated:  " + BRIGHT_GREEN + stats.getTotalMonstersDefeated() + RESET);
        System.out.println(BRIGHT_CYAN + separator + RESET + "\n");
    }

    // INPUT OPTIONS


    public static void printBattleMenu(){
        System.out.println(BRIGHT_YELLOW + "Choose an action:" + RESET);
        System.out.println(RED + "A" + RESET + " - Attack");
        System.out.println(MAGENTA + "S" + RESET + " - Cast Spell");
        System.out.println(BLUE + "P" + RESET + " - Use Potion");
        System.out.println(CYAN + "E" + RESET + " - Change Equipment");
        System.out.println(YELLOW + "I" + RESET + " - Show Heroes/Monsters Info");
        System.out.println(BRIGHT_RED + "Q" + RESET + " - Quit game");
    }

    public static void printMenu() {
        System.out.println(BRIGHT_CYAN + "\nControls:" + RESET);
        System.out.println(GREEN + "W/A/S/D" + RESET + " - move");
        System.out.println(CYAN + "I/C" + RESET + " - manage inventory (view info, equip/use items)");
        System.out.println(YELLOW + "M" + RESET + " - enter market (if on market tile)");
        System.out.println(RED + "Q" + RESET + " - quit game");
        System.out.println(BLUE + "H" + RESET + " - Help/Information");
        System.out.print(BRIGHT_YELLOW + "Your move > " + RESET);
        
    }

    public static void printMarketMenu() {
        System.out.println(BRIGHT_YELLOW + "\nMarket Controls:" + RESET);
        System.out.println(CYAN + "I" + RESET + " - Show Hero Info");
        System.out.println(GREEN + "B" + RESET + " - Buy item");
        System.out.println(YELLOW + "S" + RESET + " - Sell item");
        System.out.println(MAGENTA + "R" + RESET + " - Repair broken equipment");
        System.out.println(BLUE + "E" + RESET + " - Exit market");
        System.out.println(RED + "Q" + RESET + " - quit game");
        System.out.print(BRIGHT_YELLOW + "Your choice >" + RESET);
    }


    



    


}
