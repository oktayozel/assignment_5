package src.Core;

import java.util.Random;


public abstract class GameManager {

    private Random rand = new Random();

    private Board board;
    private Piece partyPiece;
    private User user;
    private src.Statistics.Statistics statistics;
    
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
