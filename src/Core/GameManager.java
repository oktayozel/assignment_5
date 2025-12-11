package src.Core;

import java.util.Random;
import src.Utils.Statistics.Statistics;

public abstract class GameManager {

    protected Random rand = new Random();

    protected Board board;
    protected Piece partyPiece;
    protected User user;
    protected Statistics statistics;
    
    public GameManager(Statistics statistics) {
        this.statistics = statistics;
        this.statistics.incrementGamesPlayed();
        setupGame();
    }



    abstract public void start();
    public void handleTileEvent(){
        // Default implementation does nothing
    }
    abstract public void setupGame();

    public Board getBoard() {
        return board;
    }
    public User getUser() {
        return user;
    }
    public Piece getPartyPiece() {
        return partyPiece;
    }
    public src.Utils.Statistics.Statistics getStatistics() {
        return statistics;
    }
}
