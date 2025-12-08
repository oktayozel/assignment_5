package src;
import src.Core.MainMenu;
import src.Utils.Sound.SoundManager;
// entry point for game
public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        // start music
        SoundManager.getInstance().play();  

        // start game
        mainMenu.start();
        // stop music when exiting
        SoundManager.getInstance().stop(); 


    }
}


