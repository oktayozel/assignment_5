package src;
import src.Core.GameManager;
import src.Statistics.Statistics;

// entry point for game
public class Main {
    public static void main(String[] args) {
        // make statistics object
        Statistics stats = new Statistics();
        
        // make game manager
        GameManager gameManager = new GameManager(stats);
        
        // start game
        gameManager.start();
    }
}


