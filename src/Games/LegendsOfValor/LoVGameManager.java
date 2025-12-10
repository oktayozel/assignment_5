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

import src.Core.User;

public class LoVGameManager extends GameManager {
    private List<Hero> heroes = new ArrayList<>();
    private List<String> heroLanes = new ArrayList<>();
    // Ask player for hero type and lane, create heroes

    
    private User user;

    public LoVGameManager(Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        Output.gameInitializationMessage("lov");
        Output.someSpace();


        this.user = new User(Input.getUsername());
        initializeHeroes();
        this.board = new LoVBoard();
        while(!board.isBoardPlayable()){
            this.board = new LoVBoard();
        }
        // TODO: Place heroes on board according to heroLanes

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

            // Null check and fallback
            while (chosen == null) {
                Output.print("No hero found for type " + classPlural + ". Please select again.");
                heroType = Input.readInt(1, 3);
                classPlural = heroType == 1 ? "Warriors" : heroType == 2 ? "Sorcerers" : "Paladins";
                chosen = DefaultReader.getRandomHero(classPlural);
            }

            Inventory inv = new Inventory();
            Hero hero;
            String romanNumber = (i==1?"I": i==2?"II":"III");

            if ("Warriors".equals(classPlural)) {
                Warrior warrior = new Warrior(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
                warrior.equipDefaultWeapon();
                hero = warrior;
            } else if ("Sorcerers".equals(classPlural)) {
                Sorcerer sorcerer = new Sorcerer(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
                sorcerer.equipDefaultWeapon();
                hero = sorcerer;
            } else {
                Paladin paladin = new Paladin(chosen.name + " " + romanNumber, chosen.level, chosen.HP, chosen.MP,
                    chosen.strength, chosen.dexterity, chosen.agility, chosen.gold, inv);
                paladin.equipDefaultWeapon();
                hero = paladin;
            }
            heroes.add(hero);
            Output.print("Select lane for Hero " + i + " (1: left, 2: mid, 3: right): ");
            int laneChoice = Input.readInt(1, 3);
            heroLanes.add(laneNames[laneChoice-1]);
        }
    }

    @Override
    public void handleTileEvent() {
        return;
    }

    

    
}
