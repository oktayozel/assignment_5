package src.Core;

import src.IO.Input;
import src.Statistics.Statistics;
import src.Core.GameManager; 

public class MainMenu {
    private Statistics statistics;
    public MainMenu() {
        statistics = new Statistics();

    }
 
    public void start() {
        System.out.println("Welcome to the Fantastic Games Main Menu. Hope you are ready for the adventure!");

        String game = Input.getGame();
        
        while (true) {
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