package src.Core;

import java.util.Random;


public abstract class GameManager {

    protected Random rand = new Random();

    protected Board board;
    protected Piece partyPiece;
    protected User user;
    protected src.Statistics.Statistics statistics;
    
    public GameManager(src.Statistics.Statistics statistics) {
        this.statistics = statistics;
        this.statistics.incrementGamesPlayed();
        setupGame();
    }



    abstract public void start();
    abstract public void handleTileEvent();
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
    public src.Statistics.Statistics getStatistics() {
        return statistics;
    }
}
