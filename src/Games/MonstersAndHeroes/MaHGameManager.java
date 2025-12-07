package src.Games.MonstersAndHeroes;

import src.Core.GameManager;
import src.Core.Board;
import src.Core.Piece;
import src.Core.Tile;
import src.Core.User;
import src.Default.DefaultReader;
import src.IO.Input;
import src.IO.Output;
import src.Battle.Battle;

public class MaHGameManager extends GameManager{
    
    public MaHGameManager(src.Statistics.Statistics statistics) {
        super(statistics);
    }

    // setup user party board and starting position
    public void setupGame() {
        Output.gameInitializationMessage();
        Output.someSpace();

        String name = Input.getUsername();
        this.user = new User(name);

        int partySize = Input.getPartySize();
        this.user.getParty().initializeParty(partySize);

        // make board until its playable
        this.board = new Board(DefaultReader.getDefaultSettings("board_size"));
        while(!board.isBoardPlayable()){
            this.board = new Board(DefaultReader.getDefaultSettings("board_size"));
        }   

        // find first accessible tile for party
        int startRow = 0; 
        int startCol = 0;
        boolean found = false;
        for (int r = 0; r < board.getSize() && !found; r++) {
            for (int c = 0; c < board.getSize(); c++) {
                if (board.getTile(r, c).isAccessible()) {
                    startRow = r;
                    startCol = c;
                    found = true;
                    break;   
                }
            }
        }

        this.partyPiece = new Piece(startRow, startCol);
    }


    // main game loop
    public void start() {

        Output.displaySecondWelcomeMessage(user);
        Input.setStatistics(statistics);
        
        boolean running = true;
        while (running) {
            if (!user.isInMarket() && !user.isInBattle()) {
                board.printBoard(partyPiece.getRow(), partyPiece.getCol());
                Output.printMenu();
                running = Input.getInput(this);
            }
        }
        Output.clearScreen();
        Output.displayStatistics(statistics);
        System.out.println(Output.BRIGHT_YELLOW + "Press ENTER to continue..." + Output.RESET);
        Input.waitForEnter();
        Output.print("Thanks for playing!");
    }


    // random battle on common tile
    public void handleTileEvent() {
        Tile tile = board.getTile(partyPiece.getRow(), partyPiece.getCol());
        if (tile == null) {
            return;
        }

        if (tile.isCommon()) {
            int encounterChance = rand.nextInt(100);
            if (encounterChance < DefaultReader.getDefaultSettings("battle_probability_percent")) {
                Output.narrative("OH NO! Monsters here! Battle starts now!", Output.RED);
                user.setInBattle(true);
                Battle battle = new Battle(this);
                battle.start();
            }
            else {
                Output.printRandomNoBattleMessage(encounterChance);
            }
        }
    }

}


