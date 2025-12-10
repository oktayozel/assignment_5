package src.Games.LegendsOfValor;

import java.util.List;
import src.Core.GameManager;
import src.Default.DefaultReader;
import src.IO.Input;
import src.IO.Output;
import src.Core.User;
import src.Games.MonstersAndHeroes.MaHBoard;

public class LoVGameManager extends GameManager {
    
    private User user1;
    private User user2;
    private User currentUser;

    public LoVGameManager(src.Statistics.Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        Output.gameInitializationMessage("lov");
        Output.someSpace();

        List<String> nameList = Input.getMultiplayerUsernames();

        this.user1 = new User(nameList.get(0));
        this.user2 = new User(nameList.get(1));


        this.board = new LoVBoard(DefaultReader.getDefaultSettings("board_size"));
        while(!board.isBoardPlayable()){
            this.board = new LoVBoard(DefaultReader.getDefaultSettings("board_size"));
        }   

    }

    @Override
    public void start() {
        Output.displaySecondWelcomeMessage(user1, user2);
        switchUser();
        boolean running = true;
 
        while (running) {
            System.out.println("UPS: I didn't implement this part yettt sorry");
            Output.printMenu("mah");

        }


        Output.clearScreen();
        Output.displayStatistics(statistics);
        System.out.println(Output.BRIGHT_YELLOW + "Press ENTER to continue..." + Output.RESET);
        Input.waitForEnter();
        Output.print("Thanks for playing!");
    }

    @Override
    public void handleTileEvent() {
        // TODO: Implement Legends of Valor tile events
    }

    public User switchUser() {
        if (currentUser == null || currentUser == user2) {
            currentUser = user1;
        } else {
            currentUser = user2;
        }
        return currentUser;
    }



    

    
}
