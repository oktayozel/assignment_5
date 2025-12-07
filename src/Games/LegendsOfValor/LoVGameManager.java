package src.Games.LegendsOfValor;

import java.util.List;
import src.Core.GameManager;
import src.Utils.Default.DefaultReader;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Utils.Interface.Multiplayer;
import src.Core.User;
import src.Games.MonstersAndHeroes.MaHBoard;

public class LoVGameManager extends GameManager implements Multiplayer {
    
    private User user1;
    private User user2;
    private User currentUser;

    public LoVGameManager(src.Utils.Statistics.Statistics statistics) {
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

    @Override
    public User switchUser() {
        if (currentUser == null || currentUser == user2) {
            currentUser = user1;
        } else {
            currentUser = user2;
        }
        return currentUser;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }


    
}
