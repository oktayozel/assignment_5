package src.Core;

import src.Utils.IO.Input;
import src.Utils.Statistics.Statistics;


// main menu for selecting games
public class MainMenu {
    private Statistics statistics;
    public MainMenu() {
        statistics = new Statistics();

    }
 
    // starts main menu loop
    public void start() {
        System.out.println("Welcome to the Fantastic Games Main Menu. Hope you are ready for the adventure!");

        Input.setStatistics(statistics);
        // main game loop
        
        while (true) {
            String game = Input.getGame();
            GameManager gm = null;
            if (game.equals("1")) {
                gm = new src.Games.MonstersAndHeroes.MaHGameManager(statistics);
            } else if (game.equals("2")) {
                gm = new src.Games.LegendsOfValor.LoVGameManager(statistics);
            }
            if (gm != null) {
                gm.start();
            }
        }
    }
}