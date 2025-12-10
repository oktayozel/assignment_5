package src.Games.LegendsOfValor;

import src.Core.GameManager;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Utils.Statistics.Statistics;
import src.Core.User;

public class LoVGameManager extends GameManager {
    
    private User user;

    public LoVGameManager(Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        Output.gameInitializationMessage("lov");
        Output.someSpace();


        this.user = new User(Input.getUsername());


        //this.board = new LoVBoard(DefaultReader.getDefaultSettings("board_size"));
        //while(!board.isBoardPlayable()){
        //    this.board = new LoVBoard(DefaultReader.getDefaultSettings("board_size"));
        //}   
    }

    @Override
    public void start() {
        Output.displaySecondWelcomeMessage(user);
        boolean running = true;
 
        while (running) {
            board.printBoard(-1,-1);




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





    

    
}
